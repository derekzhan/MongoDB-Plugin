

import com.cybermkd.kit.MongoQuery;
import com.cybermkd.plugin.MongoJFinalPlugin;
import com.mongodb.BasicDBObject;
import org.bson.conversions.Bson;
import org.junit.Test;

/**
 * 创建人:T-baby
 * 创建日期: 16/4/15
 * 文件描述:
 */
public class MongodbTest {

    public void init() {

        MongoJFinalPlugin jFinalPlugin = new MongoJFinalPlugin("teemo", "localhost:27017");
        jFinalPlugin.start();

    }

    @Test
    public void testInsert() {

        init();
        MongoQuery query=new MongoQuery();
        System.out.println(query.use("item").set("a", "1").set("b","This is a mongodb insert single one record.").save());

    }

    @Test
    public void testInsertList() {

        init();
        MongoQuery query=new MongoQuery();
        query.use("item")
                .add(new MongoQuery().set("a", "1").set("b", "1"))
                .add(new MongoQuery().set("a", "1").set("b", "3"))
                .add(new MongoQuery().set("a", "2").set("b", "2"))
                .add(new MongoQuery().set("a", "2").set("b", "3"))
                .saveList();

    }

    @Test
    public void testFindById(){
        init();
        MongoQuery query=new MongoQuery();
        System.out.println(query.use("item").byId("5719867dd245e01250eb6ade").find());
    }

    @Test
    public void testFindAll(){
        init();
        MongoQuery query=new MongoQuery();
        System.out.println(query.use("item").findAll());
    }

    @Test
    public void testFind(){
        init();
        MongoQuery query=new MongoQuery();
        System.out.println(query.use("item").eq("b","2").find());
    }

    @Test
    public void testLike(){
        init();
        MongoQuery query=new MongoQuery();
        System.out.println(query.use("item").eq("a", "1").like("b", "a").find());
    }

    @Test
    public void testUpdate(){
        init();
        MongoQuery query=new MongoQuery();
        System.out.println(query.use("item").eq("a","1").modify("b","wo wo ca").update());
    }

    @Test
    public void testUpById(){
        init();
        MongoQuery query=new MongoQuery();
        System.out.println(query.use("item").byId("57198840d245e04424f381d3").modify("b",4).update());
    }

    @Test
    public void testDel(){
        init();
        MongoQuery query=new MongoQuery();
        System.out.println(query.use("item").eq("b",4).delete());
    }

    @Test
    public void testFindPage(){
        init();
        MongoQuery query = new MongoQuery();
        Bson bson = new BasicDBObject("b",1);
        System.out.println(query.use("item").find(bson));
        System.out.println(query.use("item").find(bson,3));
        System.out.println(query.use("item").find(bson,1,3));
    }
}

