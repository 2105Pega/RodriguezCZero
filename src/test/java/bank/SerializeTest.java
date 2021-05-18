package bank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import diwhy.Serializer;

class SerializeTest {

	@Test
    @DisplayName("Test Serialization")
    @Tag("Startup")
    @Tag("Serialize")
	void test() {
        ThrowawayClass x = new ThrowawayClass("Jerry");
        ArrayList<Object> o = new ArrayList<>();
        ThrowawayClass y = new ThrowawayClass();
        try {
        	Serializer.write(x, "throw.txt");
        } catch(Exception e){
            e.getMessage();
        }
        try{
            o = Serializer.read("throw.txt");
            y = (ThrowawayClass)o.get(0);
        } catch(Exception e){
            e.getMessage();
        }
        assertTrue(y instanceof ThrowawayClass);		
		fail("Not yet implemented");
	}
    @Test
    @DisplayName("Test Collection Serialization")
    @Tag("Startup")
    @Tag("Serialize")
    void seializeCollectionTest(){
        ThrowawayClass a = new ThrowawayClass("a");
        ThrowawayClass b = new ThrowawayClass("b");
        ThrowawayClass c = new ThrowawayClass("c");
        ArrayList<ThrowawayClass> alphabet = new ArrayList<>(Arrays.asList(a,b,c));
        try {
            Serializer.write(alphabet, "throw.txt");
        } catch(Exception e){
            e.getMessage();
        }
        try {
            alphabet = Serializer.read("throw.txt");
        } catch (Exception e){
            e.getMessage();
        }
        assertTrue(alphabet.get(0) instanceof ThrowawayClass);;
    }
}
