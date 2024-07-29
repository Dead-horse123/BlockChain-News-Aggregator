package hust.soict.dsai.blockchain.engine;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathsManager {
    public static String getSaveFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Data\\All\\Data_full_with_entities.csv";
        return realPath;
    }
    public static String getTempFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Data\\Temp.csv";
        return realPath;
    }
    public static String getUserFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Data\\userInfomation.csv";

        return realPath;
    }
    public static String getSearchHistoryFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Search\\SearchHistory.txt";

        return realPath;
    }
    public static String getSearchFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Search\\Search.py";

        return realPath;
    }
    public static String getExactSearchFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Search\\ExactSearch.py";

        return realPath;
    }
    public static String getTrendCSVFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Search\\trending\\Trend.csv";

        return realPath;
    }
    public static String getTrendHistoryFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Search\\TrendHistory.txt";

        return realPath;
    }
    public static String getFilterHistoryFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Search\\FilterHistory.txt";

        return realPath;
    }
    public static String getFilterDataFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Search\\FilterData.py";

        return realPath;
    }
    public static String getExportFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Data\\export.csv";

        return realPath;
    }
    public static String getTagsFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Data\\All\\All_Tags.csv";

        return realPath;
    }
    public static String getCategoriesFilePath(){
        Path currentRelativePath = Paths.get("");
        String realPath = currentRelativePath.toAbsolutePath().toString();

        realPath+="\\src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Data\\All\\All_Categories.csv";

        return realPath;
    }
}
