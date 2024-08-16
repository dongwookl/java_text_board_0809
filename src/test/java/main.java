import java.util.LinkedHashMap;
import java.util.Map;
class Main {
  public static void main(String[] args) {
    Rq rq = new Rq("/usr/article/list?id=3&memberId=13&boardId=2&hit=73&writerName=신짱구");
    Map<String, String> params = rq.getParams();
    System.out.println(params);
    System.out.println(rq.getParams());
    System.out.println(rq.getParams());

    String urlPath = rq.getUrlPath();
    System.out.println(urlPath);
    System.out.println(rq.getUrlPath());
    System.out.println(rq.getUrlPath());
  }
}

class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  Rq(String url) {
    this.url = url;
  }

  public Map<String, String> getParams() {
    if(params == null) {
      params = Util.getParamsFromUrl(url);
    }

    return params;
  }

  public String getUrlPath() {
    if(urlPath == null) {
      urlPath = Util.getUrlPathFromUrl(url);
    }
    return urlPath;
  }
}

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    System.out.println("getParamsFromUrl 실행됨");
    Map<String, String> params = new LinkedHashMap<>();
    String[] urlBits = url.split("\\?", 2);
    if(urlBits.length == 1) {
      return params;
    }
    String queryStr = urlBits[1];
    for(String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2);
      if(bits.length == 1) {
        continue;
      }
      params.put(bits[0], bits[1]);
    }
    return params;
  }
  static String getUrlPathFromUrl(String url) {
    System.out.println("getUrlPathFromUrl 실행됨");
    return url.split("\\?", 2)[0];
  }
}