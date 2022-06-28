
public class StockItem {
    private String barcode;
    private String dname;
    private String dtype;
    private String brand;
    private String colour;
    private String connectivity;
    private String stockqty;
    private String originalcost;
    private String retailprice;
    private String additionalinfo;
    
    public StockItem(String stBarcode, String stName, String stType, String stBrand, String stColour, String stConnect, String stStockqty, String stOriginalcost, String stRetailprice, String stAdditionalinfo) {
        barcode = stBarcode;
        dname = stName;
        dtype = stType;
        brand = stBrand;
        colour = stColour;
        connectivity = stConnect;
        stockqty = stStockqty;
        originalcost = stOriginalcost;
        retailprice = stRetailprice;
        additionalinfo = stAdditionalinfo;
    }
    
    public StockItem(String itemsLine) {
    	String[] splitItem = itemsLine.split(", ");
    	barcode = splitItem[0];
    	dname = splitItem[1];
    	dtype = splitItem[2];
    	brand = splitItem[3];
    	colour = splitItem[4];
    	connectivity = splitItem[5];
    	stockqty = splitItem[6];
    	originalcost = splitItem[7];
    	retailprice = splitItem[8];
    	additionalinfo = splitItem[9];    	
    }
    
    public String allDetails() {
    	return barcode + ", " + dname + ", " + dtype + ", " + brand + ", " + colour + ", " + connectivity + ", " + stockqty + ", " + originalcost + ", " + retailprice + ", " + additionalinfo + "\n";
    }
    
    public String forSaleDetails() {
    	return barcode + ", " + dname + ", " + dtype + ", " + brand + ", " + colour + ", " + connectivity + ", " + stockqty + ", "  + retailprice + ", " + additionalinfo + "\n";
    }
    
    public String getBarcode() {
    	return barcode;
    }
    
    public double getPrice() {
    	return Double.parseDouble(retailprice);
    }
    
    public int getQuantity() {
    	return Integer.parseInt(stockqty);
    }
    
    public void SellOne() {
    	int itemSold = Integer.parseInt(stockqty) - 1;
    	stockqty = Integer.toString(itemSold);
    }
    
    public boolean brandEquals(String brandName) {
    	if (brand.equals(brandName)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean layoutEquals(String layoutName) {
    	if (additionalinfo.equals(layoutName)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
