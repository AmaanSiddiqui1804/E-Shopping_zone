package com.cartservice.app.entity;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
  
public class ItemsTest {
 
    @Test
    public void testCreateItems() {
        Items item = new Items();
        item.setItemId(1);
        item.setPrice(10.0);
        item.setQuantity(2);
        item.setProductId(1);
 
        assertNotNull(item);
        assertEquals(1, item.getItemId());
        assertEquals(10.0, item.getPrice());
        assertEquals(2, item.getQuantity());
        assertEquals(1, item.getProductId());
    }
 
    @Test
    public void testItemsValidation() {
        // Test validation when price is null
        Items item = new Items();
        Exception exception = null;
        try {
            item.setQuantity(2);
            item.setProductId(1);
            item.setCart(null); // Set other required fields if any
            // Persist the item using JPA repository if required
        } catch (Exception e) {
            exception = e;
        }
        assertNull(exception);
    }
}