public class Discount
{
    private String disCode;
    private double percentOff;
    private boolean isValid;

    public Discount()
    {
        this.disCode = "";
        this.percentOff = 0;
        this.isValid = false;
    }

    public void setDiscountCode(String disCode)
    {
        this.disCode = disCode;
    }

    public void setPercentageOff(double percentOff)
    {
        this.percentOff = percentOff;
    }

    public void setIsValid(boolean isValid)
    {
        this.isValid = isValid;
    }

    // Getters
    public String getDiscountCode()
    {
        return disCode;
    }

    public double getPercentageOff()
    {
        return percentOff;
    }

    public boolean getIsValid()
    {
        return isValid;
    }

    public String validateCode(String enteredCode)
    {
        switch (enteredCode)
        {
            case "Y1id390":
                this.disCode = enteredCode;
                this.percentOff = 50;
                this.isValid = true;
                return "Discount code applied: 50% off";

            case "N39dej4":
                this.disCode = enteredCode;
                this.percentOff = 25;
                this.isValid = true;
                return "Discount code applied: 25% off";

            case "D8jw792":
                this.disCode = enteredCode;
                this.percentOff = 10;
                this.isValid = true;
                return "Discount code applied: 10% off";

            default:
                this.isValid = false;
                return "Discount code is invalid";
        }
    }

    public double calculateDisAmount(double subtotal)
    {
        if (isValid)
        {
            return subtotal * (percentOff / 100);
        }
        else
        {
            return 0;
        }
    }
}
