import com.lyq.entity.UserVo;
import com.lyq.mapper.UserMapper;
import com.lyq.model.User;
import com.lyq.model.UserExample;
import org.junit.Before;
import org.junit.Test;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
import java.util.List;

public class Mytest {
    private SqlSessionFactory sqlSessionFactory = null;

    @Before

    public void init() throws Exception {

        // 1. 创建SqlSessionFactoryBuilder对象

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 2. 加载SqlMapConfig.xml配置文件

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 3. 创建SqlSessionFactory对象

        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

    }

    @Test

    public void testQueryUserById() throws Exception {
        // 4. 创建SqlSession对象
        //openSession():若括号里为true，则设置为事务自动提交。若不填则默认手动提交，将自动提交设置成false,然后调用sqlSession.commit()方法。
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //5.创建mapper,调用mapper里的方法
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //创建一个实体类Vo并增加其属性
        UserVo userVo = new UserVo();
        userVo.setId(6);
        userVo.setName("张三");
        userVo.setPwd("123456");

        //新增一个数据库model对象Po
        User user = new User();
        //调用UserVo里的initDbValue方法，将Vo转为Po
        user = userVo.initDbValue();
        //新增操作
        userMapper.insertSelective(user);

        // 7. 释放资源
        sqlSession.close();

    }

    @Test
    public void testMapper() throws Exception{
        // 4. 创建SqlSession对象
        //openSession():若括号里为true，则设置为事务自动提交。若不填则默认手动提交，将自动提交设置成false,然后调用sqlSession.commit()方法。
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //5.创建mapper,调用mapper里的方法
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //从数据库中根据主键查询出Po
        User user = userMapper.selectByPrimaryKey(6);
        //Po转Vo，用于数据展示
        UserVo userVo = new UserVo(user);
        System.out.println(userVo);

    }

    @Test
    public void testInsert() throws Exception{
        // 4. 创建SqlSession对象
        //openSession():若括号里为true，则设置为事务自动提交。若不填则默认手动提交，将自动提交设置成false,然后调用sqlSession.commit()方法。
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //5.创建mapper,调用mapper里的方法
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //创建一个实体类Vo并增加其属性
        User user = new User();
        user.setId(7);
        user.setName("周杰伦");
        user.setPwd("123456");

        userMapper.insert(user);
    }
    @Test
    public void insertSelective(){
        // 4. 创建SqlSession对象
        //openSession():若括号里为true，则设置为事务自动提交。若不填则默认手动提交，将自动提交设置成false,然后调用sqlSession.commit()方法。
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //5.创建mapper,调用mapper里的方法
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();

        user.setId(9);
        user.setName("林俊杰");
        user.setPwd("123456");
        userMapper.insertSelective(user);
//        System.out.println(userMapper.insertSelective(user));

    }
    @Test
    public void selectByExample(){
        // 4. 创建SqlSession对象
        //openSession():若括号里为true，则设置为事务自动提交。若不填则默认手动提交，将自动提交设置成false,然后调用sqlSession.commit()方法。
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //5.创建mapper,调用mapper里的方法
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //通过criteria构造查询条件
        UserExample userExample = new UserExample();
        //构造自定义查询条件
        UserExample.Criteria criteria = userExample.createCriteria();
        //查询Id为 6 的记录
        criteria.andIdEqualTo(6);
        List<User> userList = userMapper.selectByExample(userExample);
        for (User user:userList) {
            UserVo userVo = new UserVo(user);
            System.out.println(userVo);
        }




//        User user = new User();
//
//        user.setName("String");
//        userMapper.insertSelective(user);
//        System.out.println(userMapper.insertSelective(user));

    }


}
