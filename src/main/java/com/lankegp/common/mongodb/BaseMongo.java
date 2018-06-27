package com.lankegp.common.mongodb;

import com.lankegp.common.annotation.SearchField;
import com.lankegp.common.enums.SearchType;
import com.lankegp.common.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static com.lankegp.common.enums.SearchType.*;

/**
 * mongodb 基础操作类
 * Created by liugongrui on 2017/12/22.
 */
@Repository
public abstract class BaseMongo<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseMongo.class);

    @Autowired
    protected MongoTemplate mongoTemplate;

    // ＝＝＝＝＝＝＝ 增 ＝＝＝＝＝＝＝＝＝＝＝＝
    public void save(T t) {
        mongoTemplate.save(t);
        logger.debug("save entity: {}", t);
    }

    public void insertAll(List<T> list) {
        mongoTemplate.insertAll(list);
    }

    // ＝＝＝＝＝＝＝ 删 ＝＝＝＝＝＝＝＝＝＝＝＝

    /**
     * 删除对象
     *
     * @param t
     */
    public void delete(T t) {
        mongoTemplate.remove(t);
    }

    /**
     * 根据id 删除对象
     *
     * @param id
     */
    public void deleteById(String id) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = new Query(criteria);
        mongoTemplate.remove(query, this.getEntityClass());
    }

    /**
     * 根据条件删除
     */
    public void delete(Query query) {
        mongoTemplate.remove(query, this.getEntityClass());
    }

    /**
     * 删除该collection 的所有的数据
     */
    public void deleteAll() {
        mongoTemplate.dropCollection(this.getEntityClass());
    }

    // ＝＝＝＝＝＝＝ 改 ＝＝＝＝＝＝＝＝＝＝＝＝
    public void update(Query query, Update update) {
        mongoTemplate.findAndModify(query, update, this.getEntityClass());
    }

    // ＝＝＝＝＝＝＝ 查 ＝＝＝＝＝＝＝＝＝＝＝＝
    public List<T> findAll() {
        return mongoTemplate.findAll(this.getEntityClass());
    }

    /**
     * 根据查询query 查找list
     *
     * @param query
     * @return
     */
    public List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 按照字段排序 － 顺序  <br/>
     *
     * @param query      查询条件  <br/>
     * @param properties 排序字段  <br/>
     * @return
     */
    public List<T> findWithOrderAsc(Query query, String... properties) {
        Sort sort = new Sort(Direction.ASC, properties);
        query.with(sort);
        return mongoTemplate.find(query, this.getEntityClass());
    }


    /**
     * 按照字段排序 － 逆序 <br/>
     *
     * @param query      查询条件  <br/>
     * @param properties 排序字段  <br/>
     * @return
     */
    public List<T> findWithOrderDesc(Query query, String... properties) {
        Sort sort = new Sort(Direction.DESC, properties);
        query.with(sort);
        return mongoTemplate.find(query, this.getEntityClass());
    }


    /**
     * 根据查询query 查找一个对象
     *
     * @param query
     * @return
     */
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 根据 id 查询对象
     *
     * @param id
     * @return
     */
    public T findById(String id) {
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    /**
     * 根据id 和 集合名字查询对象
     *
     * @param id
     * @param collectionName
     * @return
     */
    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    /**
     * 分页查询
     * 一般用于EXTJS等框架分页
     */
    public List<T> findPage(Query query, int start, int length) {
        query.skip(start);
        query.limit(length);
        return find(query);
    }

    /**
     * 分页查询
     */
    public List<T> findPage(T entity, int start, int length) throws Exception {
        Query query = getQueryByFileds(entity);
        query.skip(start).limit(length);
        return find(query);
    }

    public long count(Query query) {
        return mongoTemplate.count(query, this.getEntityClass());
    }

    public long count(T entity) throws Exception {
        Query query = getQueryByFileds(entity);
        return mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * 获取需要操作的实体类class <br/>
     * 例如: StudentScoreDao extends MongodbDao <b>&lt;StudentScore&gt;</b> <br/>
     * 返回的是 <b>StudentScore</b> 的Class
     *
     * @return
     */
    private Class<T> getEntityClass() {
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 获取collection的名字，默认是dao范型T的名字 <br/>
     * 例如: StudentScoreDao extends MongodbDao <b>&lt;StudentScore&gt;</b> <br/>
     * 则返回的名字是：<b>StudentScore</b>
     *
     * @return
     */
    private String getCollectionName() {
        return getEntityClass().getSimpleName();
    }

    /**
     * 条件选择更新
     *
     * @param
     */
    public void updateSelective(T queryEntity, T entity) throws Exception {
        Query query = getQueryByFileds(queryEntity);
        Update update = getUpdateByFileds(entity);
        mongoTemplate.updateMulti(query, update, getEntityClass());
    }

    /**
     * id选择更新
     *
     * @param
     */
    public void updateSelective(T entity) throws Exception {
        Query query = new Query();
        PropertyDescriptor pd = new PropertyDescriptor("id", getEntityClass());
        Method getMthod = pd.getReadMethod();
        Object object = getMthod.invoke(entity);
        query.addCriteria(new Criteria("id").is(object));
        Update update = getUpdateByFileds(entity);
        mongoTemplate.updateMulti(query, update, getEntityClass());
    }

    /**
     * 根据属性值获得update
     *
     * @param entity
     * @return
     * @throws Exception
     */
    private Update getUpdateByFileds(T entity) throws Exception {
        Field[] fields = getEntityClass().getDeclaredFields();
        Update update = new Update();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Transient.class) || field.getName().equalsIgnoreCase("serialVersionUID")) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), getEntityClass());
            Method getMthod = pd.getReadMethod();
            Object object = getMthod.invoke(entity);
            if (object != null) {
                update.set(field.getName(), object);
            }
        }
        return update;
    }

    /**
     * 根据属性值获得query
     *
     * @param entity
     * @return
     * @throws Exception
     */
    private Query getQueryByFileds(T entity) throws Exception {
        Field[] fields = getEntityClass().getDeclaredFields();
        Query query = new Query();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Transient.class) || field.getName().equalsIgnoreCase("serialVersionUID")) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), getEntityClass());
            Method getMthod = pd.getReadMethod();
            Object object = getMthod.invoke(entity);
            if (object != null) {
                if (field.isAnnotationPresent(SearchField.class)) {
                    SearchField searchField = field.getAnnotation(SearchField.class);
                    SearchType searchType = searchField.type();
                    switch (searchType) {
                        case EQ:
                            query.addCriteria(new Criteria(field.getName()).is(object));
                            break;
                        case NE:
                            query.addCriteria(new Criteria(field.getName()).ne(object));
                            break;
                        case LIKE:
                            query.addCriteria(new Criteria(field.getName()).regex(object.toString()));
                            break;
                        case GT:
                            query.addCriteria(new Criteria(field.getName()).gt(object));
                            break;
                        case LT:
                            query.addCriteria(new Criteria(field.getName()).lt(object));
                            break;
                        case GTE:
                            query.addCriteria(new Criteria(field.getName()).gte(object));
                            break;
                        case LTE:
                            query.addCriteria(new Criteria(field.getName()).lte(object));
                            break;
                        case IN:
                            query.addCriteria(new Criteria(field.getName()).in(object));
                            break;
                        default:

                            break;
                    }
                } else {
                    query.addCriteria(new Criteria(field.getName()).is(object));
                }
            }
        }
        return query;
    }

}
