package translate.api.yandex.dictionary;

import java.util.List;

public class Example extends WrappedText {

    private List<Translation> tr;

    public List<Translation> getTr() {
        return tr;
    }

    public void setTr(List<Translation> tr) {
        this.tr = tr;
    }
}
