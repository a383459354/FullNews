package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class BookHotSeekBeans {

    /**
     * hotWords : ["邪医狂妻","萌妻难养，腹黑老公有代沟","我要做首辅","腹黑小狂后","神纹战记","完美宠婚：老公，我上你下","养鬼为祸","废材逆袭：冰山王爷倾城妃","网游之终极剑仙","冷少的私宠宝贝","异界之装备商人","一夜悍妃：王妃爆笑驯夫记","绝世神魂","豪门第一宠：大叔，求放过","武道争锋","傲娇小萌妃：殿下太腹黑","仙都","诱妻入局：老公矜持点","抗日之最强战兵","邪帝狂后：废材九小姐","男人当自强","豪门暖婚蜜爱","我的邻家空姐","嫡女药师：邪王的极品私宠","重生之明星奶爸","宠上毒辣小狂妻","我的美女总监","童养媳之桃李满天下"]
     * ok : true
     */

    private boolean ok;
    private List<String> hotWords;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<String> getHotWords() {
        return hotWords;
    }

    public void setHotWords(List<String> hotWords) {
        this.hotWords = hotWords;
    }
}
