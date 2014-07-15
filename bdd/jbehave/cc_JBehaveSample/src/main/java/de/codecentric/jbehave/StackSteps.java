package de.codecentric.jbehave;

import java.util.Stack;
import org.jbehave.core.annotations.*;
import org.jbehave.core.embedder.Embedder;
import junit.framework.Assert;

public class StackSteps extends Embedder{

    private Stack<String> testStack;
    private String searchElement;
 
    @Given("an empty stack")
    public void anEmptyStack() {
        testStack = new Stack<String>();
    }
    
    @When("the string $element is added")
    public void anElementIsAdded(String element) {
        testStack.push(element);
    }
 
    @When("the last element is removed again")
    public void removeLastElement() {
        testStack.pop();
    }

    @When("the element $element is searched for")
    public void searchForElement(String element) {
        searchElement = element;
    }
    
    @Then("the resulting element should be $result")
    public void theResultingElementShouldBe(String result) {
    	Assert.assertEquals(testStack.pop(), result);
    }
    
    @Then("the position returned should be $pos")
    public void thePositionReturnedShouldBe(int pos) {
    	Assert.assertEquals(testStack.search(searchElement), pos);
    }
}
