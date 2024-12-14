package es.jmpg.developer.runeterra.stream.loader.app.loader;

import es.jmpg.developer.runeterra.stream.loader.app.config.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoaderComponent {

    private final AppConfig appConfig;

    public void run(){
        try
        {
            final var file = appConfig.getLoader().getApi().getLatest();
            final var output = appConfig.getData().getOutput();

            Path zipPath = this.download(file, output);
            this.unzip(zipPath, output);
            this.process(output);
        }
        catch (IOException e)
        {
            log.error("Error al ejecutar el componente de carga", e);
        }
    }

    private Path download(String fileURL, String saveDir) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

        // Verificar el código de respuesta
        int responseCode = httpConnection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to download file: HTTP response code " + responseCode);
        }

        // Obtener el nombre del archivo desde la URL
        String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
        Path savePath = Path.of(saveDir, fileName);

        // Descargar el archivo
        try (InputStream inputStream = httpConnection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(savePath.toFile())) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        log.debug("Archivo descargado en: {}", savePath);
        return savePath;
    }

    private void unzip(Path zipPath, String outputDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath.toFile()))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Path filePath = Path.of(outputDir, entry.getName());

                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    Files.createDirectories(filePath.getParent());
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath.toFile()))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zis.read(buffer)) != -1) {
                            bos.write(buffer, 0, bytesRead);
                        }
                    }
                }
                zis.closeEntry();
                log.debug("Archivo descomprimido: {}", filePath);
            }
        }
    }

    private void process(String outputDir) throws IOException {
        Files.walk(Path.of(outputDir))
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try {
                        String content = Files.readString(file);
                        log.debug("Procesando archivo: {}", file);
                    } catch (IOException e) {
                        log.error("Error al procesar archivo: " + file, e);
                    }
                });
    }

}
