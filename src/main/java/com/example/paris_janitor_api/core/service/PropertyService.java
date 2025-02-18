package com.example.paris_janitor_api.core.service;


//import com.example.paris_janitor_api.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
        import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PropertyService  {

    /*@Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PropertyRepositoryImpl propertyRepositoryImpl;


    @Override
    public Page<Property> getProperties(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return propertyRepositoryImpl.findAll(pageable);
    }

    @Override
    public boolean existsPropertyById(String id) {
        Query query=new Query();
        query.addCriteria(new Criteria("id").is(id));
        return mongoTemplate.exists(query,Property.class);
    }

    @Override
    public Property saveProperty(Property property) {
        return propertyRepositoryImpl.save(property);
        }

    @Override
    public Optional<Property> getPropertyById(String propertyId) {
        return propertyRepositoryImpl.findById(propertyId);
    }

    @Override
    public List<Property> getProperties() {
        return mongoTemplate.findAll(Property.class);
    }

    @Override
    public Property deleteProperty(String propertyId) {
        Criteria criteria = new Criteria();
        criteria.and("id").is(propertyId);
        return mongoTemplate.findAndRemove(new Query().addCriteria(criteria),Property.class);
    }

    @Override
    public List<Property> getPropertyByUserId(String userId) {
        return propertyRepositoryImpl.findPropertiesByUserId(userId);
    }

    @Override
    public UpdateResult updatePropertyStatus(String id) {

        PropertyStatus pending = PropertyStatus.PENDING;
        PropertyStatus validated= PropertyStatus.VALIDATED;

        Optional<Property> property= propertyRepositoryImpl.findById(id);
        Query query = new Query();
        Update update = new Update();
        if(property.isPresent()){
            query.addCriteria(Criteria.where("id").is(property.get().getId()));
            if(property.get().getState().equals(pending)){
                update.set("state",PropertyStatus.VALIDATED);
            }
            if(property.get().getState().equals(validated)){
                update.set("state",PropertyStatus.PENDING);
            }
        }
        return mongoTemplate.updateFirst(query,update,Property.class);
    }


    @Override
    public List<Property> searchPropertyByCriteria(PropertySearchDto propertySearchDto) {

        Query query = new Query();
        /*if(propertySearchDto.type!=null && !propertySearchDto.type.isEmpty()){
            query.addCriteria(Criteria.where("type").is(propertySearchDto.type));
        }

        query.addCriteria(Criteria.where("rent")
                .gte(propertySearchDto.minBudget)
                .lte(propertySearchDto.maxBudget));*/
        /*if(propertySearchDto.minBudget!=null || propertySearchDto.maxBudget!=null ){

            if(propertySearchDto.minBudget==null){
                query.addCriteria(Criteria.where("rent").gte(0));
            }
            if(propertySearchDto.maxBudget==null){
                query.addCriteria(Criteria.where("rent").gte(propertySearchDto.maxBudget)
                        .gte(propertySearchDto.maxBudget));
            }
            query.addCriteria(Criteria.where("rent").gte(propertySearchDto.minBudget)
                    .lte(propertySearchDto.maxBudget));
                    //.andOperator(Criteria.where("rent").lte(propertySearchDto.maxBudget)));
        }*/
        /*if(propertySearchDto.maxBudget!=null ){
            query.addCriteria(Criteria.where("rent").lte(propertySearchDto.maxBudget));
        }*/
        /*if(propertySearchDto.location!=null && !propertySearchDto.location.isEmpty()){
            query.addCriteria(Criteria.where("address").in(propertySearchDto.location));
        }*/
       /* if(propertySearchDto.state!=null){
            switch (propertySearchDto.state){
                case "":
                    query.addCriteria(Criteria.where("status").is("PENDING"));
                    break;
                case VALIDATED:
                    query.addCriteria(Criteria.where("status").is("VALIDATED"));
                    break;
                default:
                    break;
            }
        }
        return mongoTemplate.find(query,Property.class);
    }*/


}
