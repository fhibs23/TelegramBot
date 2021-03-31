import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tests {
    public static void main(String[] args) throws IOException {
        Document document= Jsoup.connect("https://www.interesnie-fakty.ru/nauka/kompyurery/o-programmistah-i-programmirovanii/#:~:text=Представляем%20интересные%20факты%20о%20программистах,котором%20можно%20было%20писать%20программы\n")
                .userAgent("Chrome/81.0.4044.138")
                .get();
        Elements facts = document.select("ol>li");
        List<Elements> list=new ArrayList<Elements>();
        String str = facts.toString();
        String[] sub=str.split("<li>");
        System.out.println(sub[1+(int)(Math.random()*10)].replace("</li>",""));
    }
}
