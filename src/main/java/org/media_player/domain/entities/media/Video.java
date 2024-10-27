package org.media_player.domain.entities.media;

public class Video implements MediaFile {
    private String fileName;
    private String filePath;
    private String fileExtension;

    public Video(String fileName, String filePath, String fileExtension) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileExtension = fileExtension;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public String getFileExtension() {
        return fileExtension;
    }

    @Override
    public void play() {
        System.out.println("Playing video file: " + fileName);
    }

    @Override
    public void stop() {
        System.out.println("Stopping video file: " + fileName);
    }

    @Override
    public void pause() {
        System.out.println("Pausing video file: " + fileName);
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("Setting volume to: " + volume);
    }
}
