### #100DaysOfCode

# Objectives for the Challenge

- Get a better understanding of Spring Framework
- Learn how to persist data (effectively)
- Get a better understanding of Spring Data (JPA) and how to use it
- Learn how to use a NoSQL database such as MongoDB
- Learn a SQL-based Database
- Apply knowledge in some kind of project such as a small online-store backend to store user information, store items, 
user authentification etc.

# Java 

## **Differences between JVM, JDK and JRE**

JVM stand for Java Virtual Machine and is the underlying process running on a computer. The JVM
is responsible for executing programs. The JRE or Java Runtime Environment creates the JVM. 
The JDK or Java Development Kit lets you develop your Java applications.

## **Memory Management in Java**

Pre Java, each developer was responsible for taking care of memory management. Java comes with a 
built-in process called *Garbage Collection*. The Garbage Collection takes care of 
the Memory Management by identifying unused memory and de-allocating it, thus making
the memory usable again. Garbage Collection happens inside the JVM. 

### JVM Specification

The JVM is a *Virtual Machine* that runs Java class files in a portable way. Being a VM, the JVM is an abstraction of an underlying, actual machine. Unlike a *true* VM, the JVM does not create a virtual operating system. More accurate the JVM is a *managed runtime environment* or a *process virtual machine*.

### JVM implementations

A software program implements the JVM specification. There exist a lot of JVM implementations such as OpenJDK's HotSpotJVM

### JVM Instance

Downloaded program is an instance or instantiated version of the JVM. When developers talk about the JVM, it is referred to the JVM instance running in a software development or production environment. 

## **Loading and Executing class files in the JVM**

In order for the JVM to execute Java applications, it relies on the Java class loader and the Java execution engine.

### Java class loader

In order to run a Java application, a JVM must load compiled *.class* files into a context, such as a server, where they can be accessed. The Java class loader is the part the JVM that loads classes into memory and makes them available for execution. Class loader mechanism are a black box.

### Java execution engine

The JVM is responsible for allocating and maintaining the referential structure (turning keywords such as *new* operator to OS-specific requests for memory allocation).


# Spring Framework

## **Spring Web MVC**

The Spring Web MVC Framework is build around the DispatcherServlet which is responsible for dispatching of requests 
onto specific controllers. The framework makes the development of Web Application easier by reducing the amount of boilerplate
code that usually has to be maintained as a developer such as server configuration. 

The DispatcherServlet acts as a Front Controller (see Front Controller pattern as one of the Enterprise Application Patterns)
which receives incoming HTTP requests and processes them. The processing happens by mapping requests on to relevant controllers
in assistance of handler mappings.

### SOLID Design Principles

TODO

### Inversion of Control (IOC) and Dependency Injection (DI)

TODO

### RestController & ResponseBody

The annotation <code>@RestController</code> includes the annotations <code>@Controller</code> and <code>@ResponseBody</code>
thus eliminating the necessity to additionally annotate a method with <code>@ResponseBody</code>. <code>@Controller</code> was
used before the introduction of <code>@RestController</code> in Spring Web MVC 4.0. With the  <code>@Controller</code> annotation
a method was marked as a classic controller and thus being able to handle web requests. In combination with <code>@RequestMapping</code>
the controller can be exlusively implemented to only serve requests at a specific URL e.g.

        @RestController
        public class HomeController {
            
               @RequestMapping(value = "/home", method = RequestMethod.GET)
               public String home() {
                    return "Hello Spring!";
               }
        }

The class <code>HomeController</code> is marked as a controller thus the <code>DispatcherServlet</code> dispatches 
web requests onto the methods of the <code>HomeController</code>-class. The <code>@RequestMapping</code>-annotation 
implies that web requests (in this case HTTP GET requests) are mapped onto the <code>home</code> method. 

The <code>@RestController</code> is a specialized version of the controller whereas every request handling method 
of the controller class automatically serializes return objects into HttpResponse. 

Additional annotations such as <code>@RequestParam</code> and <code>@Pathvariable</code> allow a more fine-granular
implementation of controller classes and request handling methods. The  <code>@Pathvariable</code> annotation is used within 
a <code>@RequestMapping</code> method to grant access to the parts of an URI template. A URI (Uniform Resource Identifier) 
is a character string that is used for identification of an abstract or physical resource. URIs refer to resources on the WWW.

To get access to a parts of a URI-string, URI templates are used within the <code>@RequestMapping</code> annotation. 
An URI template is a URI-like string that contains variables, e. g.

        @RestController
        public class HomeController {
            
               @RequestMapping(value = "/home/customers/customer/{customerId}", method = RequestMethod.GET)
               public String home(@Pathvariable String customerId) {
                    return "Hello " + customerId;
               }
        }
        
In this case the URI-template contains a variable <code>customerId</code>. If a precise value is substituted for the
variable, the template becomes an URI. The <code>@Pathvariable</code> annotation binds the URI-template variable
to the method parameter <code>customerId</code>. In order to correctly bind the template variable to the method parameter
the variable name must either match the method parameter by name such as in the previous example, or this information
must be provided inside the annotation such as 

        @RestController
        public class HomeController {
            
               @RequestMapping(value = "/home/customers/customer/{customerId}", method = RequestMethod.GET)
               public String home(@Pathvariable("customerId") String theCustomer) {
                    return "Hello " + customerId;
               }
        }

There can be any number of Pathvariable-annotations inside a <code>@RequestMapping</code>-method. Also, Pathvariables can
be expressed by Regular Expressions for more complex Pathvariables. 

# Testing Spring Boot Applications

Testing Spring Boot applications comes down to three different kind of test *layers*:

- Web or Controller layer
- Business or Service layer 
- Data layer

On top of that there are integration tests that make use of the collaboration of multiple layers. 
Testing each layer can be described as testing the workflow of your application. 

## **Testing the Web layer**

Consider the following RestController:

        @RestController
        public class CustomerController {
            
            @RequestMapping(value = "/hello", method = RequestMethod.GET)
            public String helloWorld() {
                    businessService.sayHello();
            }
        }
        

If you would like to test this controller you will be testing the web layer of your application.
A unit test for this controller will test if the value returned from this controller, does 
match the expected value. A unit test for this controller could look like this:

        @RunWith(SpringRunner.class)
        @WebMvcTest(CustomerController.class)
        public class ControllerTest {

            @Autowired
            MockMvc mock;
            
            @MockBean
            BusinessService businessService;
        
            @Test
            public void helloWorldTest_basic() {
            
                when(businessService.sayHello()).thenReturn(
                    new String("Hello World!")
                );

                RequestBuilder request = MockMvcRequestBuilders
                        .get("/hello")

                MvcResult result = mock.perform(request)
                        .andExpect(status().isOk())
                        .andReturn();
                
                assertEquals(result.getResponse(), "Hello World!");
            }
        }     

The businessService is marked as a mock Bean, whereas the MockMvc object is used to execute
requests. The when function can be used to mock a function call to one of the functions provided by
the businessService class. With the RequestBuilder class an individual request can be build. There is a 
RequestBuilder for each HTTP requests such as GET, PUT, POST, DELETE, ...
The MvcResult class is used to get the response from the request. 


MockMVC class is main entry point for testing controllers. Request can be sent to controllers by calling
MockMVCs *perform(RequestBuilder requestBuilder)* method. Write assertions for the retrieved response with 
static methods of *MockMVCResultMatchers* class.


#### Testing the Service layer

TODO

# Java Persistance API
=======
- Apply knowledge in some kind of project such as a small online-store backend to store user information, store items, user authentification etc.

# Data Persistance

## MongoDB
TODO
### What is MongoDB?
TODO
### Features

### Adv. & Dis.
TODO

### Document Structure and working with Collections

Documents are basically JSON (JavaScript Object Notation) files which are structured as follows

        {
            field1: value1,
            field2: value2,
            array: [field1, field2],
            address: {
                street: value,
                city: value
            }
        }

JSON is a common file structure used in WWW to transmit data between clients and server. 


If a document is generated & inserted into a collection with 

        db.customer.insert({first_name: "John", last_name: "Doe"});

you can retrieve the document by using

        db.customer.find();

this returns a list of all objects in the collection as a JSON, e. g.

        { "_id" : ObjectId("5c222e425b987e0c6ac7485b"), "first_name" : "John", "last_name" : "Doe" }

The Id field was generated automatically by Mongo. In a Relation Database, this has to be done manually. Also you would have to take care of automatically incrementing the ID as well as setting it as the Primary Key in order to uniquely identify each object of your collection. 

Unlike RDBS, in a NoSQL database you don't have to specify the columns, e. g. if you create the following objects

        db.customer.insert([
            {first_name: "John", last_name: "Doe"},
            {first_name: "Steven", last_name: "Smith"},
            {first_name: "Steven", last_name: "Smith", gender: "male"},
            ]);

Notice that the last object holds an additional attribute the others don't. This would cause an error inside a RDBS because a gender columns hasn't been specified. But within Mongo this doesn't cause any harm and the objects are created with the last one holding an additional attribute specifying its gender.

Updating an object is almost the same as creating one. If you want to update an object in a collection you have to specify the following:

1. Some kind of predicate to identify the object you want to update
2. fields and values that you want to update

So for example if you want to update the customer "John Doe" you would use:

        db.customer.update({
            {first_name: "John"}, // identifier
            {
                first_name: "John", 
                last_name: "Doe",
                gender: "male"
            }
        }):

Note that the identifier uses the field "first_name" to look for the object. In a real-life application it is not recommended to use an attribute such as first_name since their could be objects that carry the same value. As you might have realized, in order to update an object you have to specify all fields of the object even if you only want to add an additional attribute or change one field value. This can become quite complex and time-consuming if the object has 10+ attributes. The following key-word can be used to lessen the pain of updating an object:

        db.customer.update({
            {first_name: "John"}, // identifier
            {
                $set: {gender: "male"}
            }
        }):

By using $set, the query is told to keep all previous information of the object and merely changing the attribute that is passed in after the set key-word. 


### MongoDB Syntax and some basic functions

        // create database
        // also switches directly into dbs
        use <name>

        // create a user that operates on db
        db.createUser({
            user: "dominik",
            pwd: "1234",
            roles: ["readWrite", "dbAdmin"]
        });

        
        /**
        * Create a collection:
        * Collections are similar * to tables in RDBS, they 
        * hold documents or records
        **/ 
        db.createCollection('customer');

        // insert object into collection
        db.customer.insert({first_name: "John", last_name: "Doe"});

        // insert multiple objects into collection
        db.customer.insert([
            {first_name: "John", last_name: "Doe"},
            {first_name: "Steven", last_name: "Smith"}
            ]);

        // update an object 
        db.customer.update({
            {first_name: "John"}, // used to identify the object that should be changed
            {first_name: "John", last_name: "Doe", gender: "male"}
        });

        // update an object with set
        db.customer.update({
            {first_name: "John"}, // used to identify the object that should be changed
            {$set:{ gender: "male"} // set saves all information previous the new attribute
        });

        // update an object with inc
        db.customer.update({
            {first_name: "John"}, 
            {$inc:{ age: "5"} // increments value by five
        });

        // remove a field from an object
        db.customer.update({
            {first_name: "Steven"},
            {$unset:{age: 1}}
        });

        // upsert if an object that should be updated is not existent
         db.customer.update({
            {first_name: "Maria"}, // used to identify the object that should be changed
            {first_name: "Maria", last_name: "Smith", gender: "female"},
            {upsert: true}
        });

        // remove an object
        db.customer.remove({
            {first_name: "Steven"}
        });

        // find an object with or
        db.customer.find({
            {$or:[{first_name: "Sharon"}, {first_name: "Troy"}]}
        });

        // find an object in assistance of lt (less than)function
        db.customer.find({
            age: {$lt: 40}
        });

        // find an object with value of a multi-attribute field
        db.customer.find({  
            {"address.city":"Boston"}
        });

        // Query for an array
        db.customer.find({memberships: "mem1
        });

        // sorting
        db.customer.find().sort({last_name: 1} // 1 sorts in ASC, whereas 2 sorts in DESC
        );

        // count documents
        db.customer.find({gender: "male"}).count();   

        or

        db.customer.find().count();

        or 

        db.customer.find().limit(4); // retrieves the first four

        // Iterating using for-each loop
        db.customer.find().forEach(function(doc) {print("Customer Name: " + doc.first_name)
        });
        
        CMD ["java", "-jar", "spring-data-0.0.1-SNAPSHOT.jar"]
        
        
        ARG JAR_FILE
        COPY ./build/libs/spring-data-0.0.1-SNAPSHOT.jar /usr/src/customer/
        WORKDIR /usr/src/customer
        
        ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongo/customers","/spring-data-0.0.1-SNAPSHOT.jar"]
        
