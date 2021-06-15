import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import javax.ws.rs.core.Application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Parser extends Application {
    /**
     * метод find парсит сайт, ищет информацию по введенному языку и выводит нужную
     * @param substr введенный язык - подстрока для поиска
     * @return нужный абзац
     * @throws IOException
     */
    public String find(String substr) throws IOException {
    Document document=Jsoup.connect("https://rb.ru/story/20-code-languages-to-learn/")
            .userAgent("Chrome/81.0.4044.138")
            .get();
    Elements h2 = document.select("[itemprop=\"articleBody\"]");
    //System.out.println(h2);
    List<Elements> list=new ArrayList<Elements>();
    String str = h2.toString();
    String[] sub=str.split("<h2>");
        int ind=0;
        for (int i=0;i<sub.length;i++){
            if (sub[i].startsWith(substr)) {
                ind=i;
                break;
            }
        }
        if (ind!=0) {
            sub[ind] = sub[ind].replace("<div class=\"article__content-block\" itemprop=\"articleBody\"></div>", "");
            sub[ind] = sub[ind].replace("<div class=\"article__content-block abv\" itemprop=\"articleBody\">", "");
            sub[ind] = sub[ind].replace("</h2>", "");
            sub[ind] = sub[ind].replace("<p>", "");
            sub[ind] = sub[ind].replace("</p>", "");
            sub[ind] = sub[ind].replace("<strong>", "");
            sub[ind] = sub[ind].replace("</strong>", "");
            sub[ind] = sub[ind].replace("<a href=\"https://code.visualstudio.com/\" target=\"_blank\">", "");
            sub[ind] = sub[ind].replace("<a class=\"fancybox\" href=\"https://media.rbcdn.ru/media/upload_tmp/2018/emile-perron-xrvdyzrgdw4-unsplash_K2C8j3h.jpg\" target=\"_blank\"><img height=\"421\" src=\"https://media.rbcdn.ru/media/thumb_h/9b/84/9b840450bdf15a61ff0396887e9b50e6/emile-perron-xrvdyzrgdw4-unsplash_K2C8j3h.jpg.750x421_q95.jpg\" style=\"display: block; margin-left: auto; margin-right: auto;\" width=\"750\"/></a>", "");
            sub[ind] = sub[ind].replace("<a href=\"https://clojure.org/\" target=\"_blank\">", "");
            sub[ind] = sub[ind].replace("</a>", "");
            sub[ind] = sub[ind].replace("<a href=\"https://translate.google.com/translate?hl=ru&amp;prev=_t&amp;sl=en&amp;tl=ru&amp;u=https://en.wikipedia.org/wiki/Dennis_Ritchie\" target=\"_blank\">", "");
            sub[ind] = sub[ind].replace("<a href=\"https://translate.google.com/translate?hl=ru&amp;prev=_t&amp;sl=en&amp;tl=ru&amp;u=https://en.wikipedia.org/wiki/Z_shell\" target=\"_blank\">zsh", "");
            sub[ind] = sub[ind].replace("<a class=\"fancybox\" href=\"https://media.rbcdn.ru/media/upload_tmp/2018/emile-perron-xrvdyzrgdw4-unsplash_K2C8j3h.jpg\" target=\"_blank\"><img height=\"421\" src=\"https://media.rbcdn.ru/media/thumb_h/9b/84/9b840450bdf15a61ff0396887e9b50e6/emile-perron-xrvdyzrgdw4-unsplash_K2C8j3h.jpg.750x421_q95.jpg\" style=\"display: block; margin-left: auto; margin-right: auto;\" width=\"750\">", "");
            return sub[ind];
        }
        else return null;
 }

    /**
     * метод findFacts() парсит сайт и выводит рандомный факт с этого сайта
     * * @return рандомный факт
     * @throws IOException
     */
 public String findFacts() throws IOException {
     Document document= Jsoup.connect("https://www.interesnie-fakty.ru/nauka/kompyurery/o-programmistah-i-programmirovanii/#:~:text=Представляем%20интересные%20факты%20о%20программистах,котором%20можно%20было%20писать%20программы\n")
             .userAgent("Chrome/81.0.4044.138")
             .get();
     Elements facts = document.select("ol>li");
     List<Elements> list=new ArrayList<Elements>();
     String str = facts.toString();
     String[] sub=str.split("<li>");
     return (sub[1+(int)(Math.random()*10)].replace("</li>",""));
    }
}




