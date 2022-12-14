/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pws.c.praktikum4.project4;

/**
 *
 * @author Lenovo
 */
import Model.Product;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProductServiceController {
   private static Map<String, Product> productRepo = new HashMap<>();
   static {
       //menambahkan product qty dan price 
      Product honey = new Product();
      honey.setId("1");
      honey.setName("Honey");
      honey.setQty("3");
      honey.setPrice("13000");
      productRepo.put(honey.getId(), honey);
      
      
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Almond");
      almond.setQty("3");
      almond.setPrice("13000");

      productRepo.put(almond.getId(), almond);
   }
  @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) {
       //memanggil pesan exception ketika id yang ingin dihapus 
       if(!productRepo.containsKey(id)){
           return new ResponseEntity<>("ID tidak ditemukan untuk delete, cek lagi", HttpStatus.OK);
       }
       else{
           //ketika id yang ingin dihapus sudah ada
           productRepo.remove(id);
           return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
       }
      
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
       
       //memanggil pesan exception  ketika id yang akan diupdate tidak tersedia
       if(!productRepo.containsKey(id)){
           return new ResponseEntity<>("ID tidak ditemukan dan di update, cek lagi", HttpStatus.OK);
       }
       else{
           //fungsi ketika id yang ingin diupdate sudah ada
           productRepo.remove(id);
           product.setId(id);
           productRepo.put(id, product);
           return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
       }
       
      
   }
   
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
       //digunakan untuk  ketika id yang ingin dibuat sudah ada
       if(productRepo.containsKey(product.getId())){
           return new ResponseEntity<>("Id tidak bisa di duplikat", HttpStatus.OK);
       }
       else{
           //berfungsi  untuk menambahkan produc
           productRepo.put(product.getId(), product);
           return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
       }
      
   }
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
}

