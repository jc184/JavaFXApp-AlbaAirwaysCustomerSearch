/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapp.albaairwayscustomersearch;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.END_OBJECT;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
import static javax.json.stream.JsonParser.Event.START_OBJECT;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author james
 */
@Provider
@Consumes({"application/json"})
public class CustomerMessageBodyReader implements MessageBodyReader<List<Customer>> {

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return true;
    }

    @Override
    public List<Customer> readFrom(Class<List<Customer>> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {
        if (mt.getType().equals("application") && mt.getSubtype().equals("json")) {
            Customer customer = new Customer();
            List<Customer> customers = new ArrayList();
            JsonParser parser = Json.createParser(in);
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        customer = new Customer();
                        break;
                    case END_OBJECT:
                        customers.add(customer);
                        break;
                    case KEY_NAME:
                        String key = parser.getString();
                        parser.next();
                        switch (key) {
                            case "customerId":
                                customer.setCustomerId(parser.getInt());
                                break;
                            case "firstName":
                                customer.setFirstName(parser.getString());
                                break;
                            case "surname":
                                customer.setSurname(parser.getString());
                                break;
                            case "mobileNo":
                                customer.setMobileNo(parser.getString());
                                break;
                            case "homePhoneNumber":
                                customer.setHomePhoneNumber(parser.getString());
                                break;
                            case "emailAddress":
                                customer.setEmailAddress(parser.getString());
                                break;
                            case "loginName":
                                customer.setLoginName(parser.getString());
                                break;
                            case "loginPassword":
                                customer.setLoginPassword(parser.getString());
                                break;
                            case "dateOfBirth":
                                customer.setDateOfBirth(parser.getString());
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
            return customers;
        }
        throw new UnsupportedOperationException("Not supported MediaType: " + mt);
    }
}
