package se.kth.iv1350.saleProcess.model;

/**
 * A Singleton class that creates instances of classes implementing algorithms for finding available discounts.
 * Classes created by this Singleton implement <code>DiscountRule</code>.
 */
public class DiscountRuleFactory {
    private final static DiscountRuleFactory DISCOUNT_RULE_FACTORY = new DiscountRuleFactory();

    /**
     * @return Returns a single instance of <code>DiscountRuleFactory</code>
     */
    public static DiscountRuleFactory getFactory(){
        return DISCOUNT_RULE_FACTORY;
    }

    private DiscountRuleFactory(){

    }

    /**
     * @return Returns the default <code>DiscountRule</code>
     */
    public DiscountRule getDefaultDiscountRule(){
        return createComposite();
    }

    private DiscountRule createComposite(){
        CompositeDiscountRule composite = new CompositeDiscountRule();
        addDefaultDiscountRules(composite);
        return composite;
    }

    private void addDefaultDiscountRules(CompositeDiscountRule composite){
        composite.addDiscountRule(new MembershipSpecificDiscount());
        composite.addDiscountRule(new ItemSpecificDiscount());
        composite.addDiscountRule(new TotalPriceSpecificDiscount());
    }

}
