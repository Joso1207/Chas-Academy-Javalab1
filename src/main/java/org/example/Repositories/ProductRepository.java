package org.example.Repositories;

import org.example.Containers.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {

    //Products are not going to be adjusted during runtime, But we want to order the list and typically would fetch by index.
    //LinkedHashmap Would work to but as the order hangs on Key-insertion I feel like ArrayList is the preferable.

    //If IndexOf is needed used we might need to reevaluate
    private List<Product> productList = new ArrayList<>();

    public ProductRepository(List<Product> productList){
        this.productList = productList;
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



}
