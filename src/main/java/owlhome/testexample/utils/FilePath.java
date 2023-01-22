package owlhome.testexample.utils;

public enum FilePath {
    LOCATION_FILE("C:\\Users\\kosav\\Desktop\\test-example\\src\\main\\fromUpload\\");

    private final String filePath;

    FilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath(){
        return this.filePath;
    }
}
