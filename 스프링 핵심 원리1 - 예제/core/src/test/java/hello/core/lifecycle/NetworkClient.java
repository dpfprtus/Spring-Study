package hello.core.lifecycle;

//javax는 자바진영에서 공식적으로 지원하는 라이브러리들이다.
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("새성자 호출, url =  " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: "+url+" message = "+ message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.inits");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
