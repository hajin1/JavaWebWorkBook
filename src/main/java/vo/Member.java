package vo;

import lombok.Getter;
import java.util.Date;

public class Member {
    @Getter
    protected int no;
    @Getter
    protected String name;
    @Getter
    protected String email;
    @Getter
    protected String password;
    @Getter
    protected Date createDate;
    @Getter
    protected Date modifiedDate;

    public Member setNo(int no){
        this.no=no;
        return this;
    }

    public Member setName(String name){
        this.name=name;
        return this;
    }

    public Member setEmail(String email){
        this.email=email;
        return this;
    }

    public Member setPassword(String password){
        this.password=password;
        return this;
    }

    public Member setCreateDate(Date createDate){
        this.createDate=createDate;
        return this;
    }

    public Member setModifiedDate(Date modifiedDate){
        this.modifiedDate=modifiedDate;
        return this;
    }

}
