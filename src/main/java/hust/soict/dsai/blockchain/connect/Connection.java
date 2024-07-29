package hust.soict.dsai.blockchain.connect;

public interface Connection {
    String hostName = "localhost:3306";
    String dbName = "data";
    String username = "root";
    String password = "huy0913362137";
    public Connection connect();
}
