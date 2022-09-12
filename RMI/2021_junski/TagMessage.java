/* 
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ Priprema ispita iz predmeta Distribuirani sistemi                       │
  │ Ispitni rok: Junski 2021                                                │
  │ 21.06.2021                                                              │
  │                                                                         │
  │ https://blanketi.sicef.info/elfak/pregled/2765                          │
  │                                                                         │
  │ Djordje Antic - Sep 12, 2022                                            │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TagMessage implements Serializable {
    public User m_user;
    public String m_msg;
    public List<String> m_tags;

    public TagMessage(User user, String msg, List<String> tags) {
        super();
        this.m_user = user;
        this.m_msg = msg;
        this.m_tags = new ArrayList<>();
        for (String string : tags) {
            this.m_tags.add(string);
        }
    }
    
}