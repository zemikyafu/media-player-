package org.media_player.domain.entities.media;

public class Audio implements MediaFile {
    private String fileName;
    private String filePath;
    private String fileExtension;

    public Audio(String fileName, String filePath, String fileExtension) {
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
        System.out.println("Playing audio file: " + fileName);
    }

    @Override
    public void stop() {
        System.out.println("Stopping audio file: " + fileName);
    }

    @Override
    public void pause() {
        System.out.println("Pausing audio file: " + fileName);
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("Setting volume to: " + volume);
    }


    public void setTreble(int treble) {
        System.out.println("Setting treble to: " + treble);
    }

    public void setBass(int bass) {
        System.out.println("Setting bass to: " + bass);
    }

}
