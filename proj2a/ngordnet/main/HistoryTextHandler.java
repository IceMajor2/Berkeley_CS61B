package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {

    private NGramMap ngm;

    public HistoryTextHandler(NGramMap map) {
        this.ngm = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        StringBuilder sb = new StringBuilder("");
        for(String word : words) {
            TimeSeries ts = ngm.weightHistory(word, startYear, endYear);
            sb.append(word);
            sb.append(": ");
            sb.append(ts.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

}
