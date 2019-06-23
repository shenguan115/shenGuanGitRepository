package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.singletonList;

@RestController
@RequestMapping("/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("find")
    public List<Customer> find(){
        List<Customer> customerList = mongoTemplate.findAll(Customer.class);
        MongoCollection<Document> collection = mongoTemplate.getCollection("inventory");
        Document canvas = new Document("item", "canvas")
                .append("qty", 100)
                .append("tags", singletonList("cotton"));

        Document size = new Document("h", 28)
                .append("w", 35.5)
                .append("uom", "cm");
        canvas.put("size", size);
        collection.insertOne(canvas);
        FindIterable<Document> findIterable = collection.find(new Document());
        System.out.print(customerList.toString());
        System.out.print(findIterable.toString());
        return customerList;
    }

    @RequestMapping("findByFirstName")
    public Customer findByFirstName(){
        Customer customer = customerService.findByFirstName("shenguan");
        return customer;
    }

    @RequestMapping("findByLastName")
    public List<Customer> findByLastName(){
        List<Customer> customerList = customerService.findByLastName("shenguan");
        return customerList;
    }
}
