package org.example.Repositories;


import org.example.Containers.Product;


import java.util.*;
import java.util.stream.Collectors;



public class ProductRepository {

    //Products are not going to be adjusted during runtime, But we want to order the list and typically would fetch by index.
    //LinkedHashmap Would work to but as the order hangs on Key-insertion I feel like ArrayList is the preferable.

    //If IndexOf is needed used we might need to reevaluate
    private List<Product> productList = new ArrayList<>();

    public ProductRepository(List<Product> productList){
        this.productList = productList;
    }

    public ProductRepository(){
        populateRepository();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Product> filteredListByCategory(String category) {
        return productList.stream().filter(product ->
                        product.getCategory()
                                .equals(category))
                                .sorted(Comparator.comparing(Product::getPrice).reversed())
                                .collect(Collectors.toList());
    }

    public List<Product> sortByPrice(){
         return productList.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).toList();
    }

    public Product productByID(int id){
        return productList.stream().filter(p->p.getId()==id).findAny().orElse(null);
    }

    public List<String> getAllCategories(){
        return productList.stream().map(Product::getCategory).toList();
    }


    private void populateRepository(){

        productList = Arrays.asList(
                new Product(20,"twoByFour","Carpentry"),
                new Product(5,"Nails","Carpentry"),
                new Product(8,"Glue","Hobbyist"),
                new Product(50,"PrintingPaper","Office Supplies"),
                new Product(4,"Stapler","Office Supplies"),
                new Product(2,"Glitter","Hobbyist"),
                new Product(100,"ComputerMouse","Computers"),
                new Product(20,"USB-C","Computers"),
                new Product(23,"Keyboard","Computers"));

    }


}
