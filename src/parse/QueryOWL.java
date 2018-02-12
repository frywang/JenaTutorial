 /** 
  * 本代码来自http://blog.csdn.net/xiaocong1314/article/details/8439696#comments
  *
  */



package parse;

  
import java.io.FileInputStream;  
import java.util.Iterator;  
import com.hp.hpl.jena.ontology.OntModelSpec;  
import java.io.IOException;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.ontology.*;  
import com.hp.hpl.jena.rdf.model.*;

  
public class QueryOWL  {  
	private static final Object StatementImpl = null;
	public static String ns = "http://www.360iii.org/ontologies/fruit#"; 
	
	
    /* 函数功能说明 
     *  
     * 创建本体模型，然后读取.owl文件，加载模型 
     *  
     * */	
	public OntModel createOnt(String owlpath) {  
		
		OntModel ontModel = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
        try
        {
        	ontModel.read(new FileInputStream("./data/食材.owl"),"");
        }
        catch(IOException ioe)
        {
               System.err.println(ioe.toString());
        }
        
		return ontModel; 

	}
	
	
	  
    
	  
    /*函数功能说明 
     *  
     * 获取owl文件中所有的class,内容包括： 
     * 类URI,类名,类描述类型,类描述值 
     *  
     */  
        public void getAllClasses(String owlpath) {  
            OntModel ontModel = this.createOnt(owlpath);  
            String str;  
            // 列出所有的class  
              
            for (Iterator allclass = ontModel.listClasses(); allclass.hasNext();) {  
                  
                OntClass ontclass = (OntClass) allclass.next();  
                if(!ontclass.isAnon()){  
                String classstr = ontclass.toString();  
                System.out.print("类URI：" + classstr + "   ");  
                str = classstr.substring(classstr.indexOf("#") + 1);  
                System.out.print("类名：" + ontclass.getLabel("zh") + "   ");  
                if (!ontclass.listSuperClasses().hasNext()) {  
                                  
                    System.out.println("类描述类型：无");  
                } else {  
                    for (Iterator supclasses = ontclass.listSuperClasses(); supclasses  
                            .hasNext();) {  
                        OntClass supclass = (OntClass) supclasses.next();  
                        String supclasstr = supclass.toString();  
                        str = supclasstr.substring(supclasstr.indexOf("#") + 1);  
                        System.out.print("类描述类型：subClassOf   ");  
                        System.out.println("类描述值：" + supclass.getLabel("zh"));  
                          
                          
                    }  
                }  
                }  
            }  
        }  
  
        /* 函数功能说明 
         *  
         * 获取owl文件中所有的属性,内容包括： 
         * 属性URI,属性名,属性,定义域,值域 
         *  
         * */  
        public void getAllProperties(String owlpath) {  
            OntModel ontMdel = this.createOnt(owlpath);  
            String str;  
//            OntProtyDAO dao = new OntProtyDAO();  
              
            // 列出所有的对象属性  
            System.out.print("对象属性\r");  
            for (Iterator allobjpry = ontMdel.listObjectProperties(); allobjpry  
                    .hasNext();) {  
  
                OntProperty objpry = (OntProperty) allobjpry.next();  
                  
                // 属性URI  
                String objprystr = objpry.toString();  
                System.out.print("属性URI：" + objprystr + "  ");  
                // 属性名  
//                str = objprystr.substring(objprystr.indexOf("#") + 1);  
                System.out.print("属性值：" + objpry.getLabel("zh") + " 属性：OP ");  

                try{
                    // 属性定义域  
                  String domain = objpry.getDomain().toString();  
                  String domainstr = domain.substring(domain.indexOf("#") + 1);  
                	System.out.print("定义域 ：" + objpry.getDomain().getLabel("zh"));  
                }catch(Exception e){
                	System.out.print("定义域 ：" + "无");  
                }
                
                try{
                // 属性值域  
//                String range = objpry.getRange().toString();  
//                String rangestr = range.substring(range.indexOf("#") + 1);  
                System.out.println("  值域：" + objpry.getRange().getLabel("zh"));  
                }catch(Exception e){
                	System.out.print("  值域：" + "无\n");  
                }
            }  
            
            
            // 列出所有的数据属性  
            System.out.print("数据属性\r");  
            for (Iterator alldatapry = ontMdel.listDatatypeProperties(); alldatapry  
                    .hasNext();) {  
  
                OntProperty datapry = (OntProperty) alldatapry.next();  
                // 属性URI  
                String dataprystr = datapry.toString();  
                System.out.print("属性URI：" + dataprystr + "  ");  
                // 属性名  
                str = dataprystr.substring(dataprystr.indexOf("#") + 1);  
                System.out.print("属性值：" + datapry.getLabel("zh") + " 属性：DP ");  
                
                // 属性定义域  
                try{
            	String domain = datapry.getDomain().toString();  
                String domainstr = domain.substring(domain.indexOf("#") + 1);  

                System.out.print("定义域 ：" + datapry.getDomain().getLabel("zh"));  
                    
                }catch(NullPointerException npe){
            	System.out.print("定义域 ：" + "无");  
                }catch(Exception e1){
            	System.out.print("定义域 ：" + "无"); 
                }
                
                // 属性值域  
                try{
                String range = datapry.getRange().toString();  
                String rangestr = range.substring(range.indexOf("#") + 1);  
                System.out.println("  值域：" + rangestr);  
                System.out.println(dataprystr );  
                System.out.println(str);  
//                System.out.println(domainstr);  
                System.out.println(rangestr);  
                }catch(NullPointerException npe){
            	System.out.print("  值域：" + "无");  
                }catch(Exception e1){
            	System.out.print("  值域：" + "无"); 
                }
            }  
        }  
  
        /* 函数功能说明 
         *  
         * 列出所有的属性特征,内容包括： 
         * 属性URI、属性名、特征类型、特征值 
         *  
         * */  
        public void getAllProcharac(String owlpath) {  
            OntModel ontMdel = this.createOnt(owlpath);  
            String str;  
            // 列出所有的对象属性  
            try{
            for (Iterator allobjpry = ontMdel.listObjectProperties(); allobjpry.hasNext();) {  
  
                String info = null;  
                OntProperty objpry = (OntProperty) allobjpry.next();  
                OntProperty objinverof = objpry.getInverseOf();  
                  
//                if(objinverof!=null){  
//                info = "属性URI ："+objpry+"\n属  性  名  :"+objpry.toString().substring(objpry.toString().indexOf("#")+1)  
//                        +"\n特征类型 :inverseOf  "+"\n特  征  值  ："+ objinverof.toString().substring(objinverof.toString().indexOf("#")+1);  
//                System.out.println(info);  
//                }  
            } 
            }catch(NullPointerException npe){
            	System.out.print("无");  
            }catch(Exception e1){
        	System.out.print("无"); 
            }
            }  
          
          
        /* 函数功能说明 
         *  
         * 列出所有的实例,内容包括： 
         * 实例URI、实例名、类URI、属性URI、属性值 
         *  
         * */  
        public void getAllIndividuals(String owlpath) {  
            OntModel ontModel = this.createOnt(owlpath);  
            String str;  
  
            // 迭代出本体文件中所有的实例  
            for (Iterator allIndivs = ontModel.listIndividuals(); allIndivs.hasNext();) {  
                Individual indiv = (Individual) allIndivs.next();  
                //对象属性命名空间  
                String namespace = indiv.toString().substring(0,indiv.toString().indexOf("#") + 1);  
                // 实例所属的类  
                OntClass classOfIndiv = indiv.getOntClass();  
                  
                //实例所属类的所有属性  
                for (Iterator classPryOfIndivs = classOfIndiv.listProperties(); classPryOfIndivs.hasNext();)   
                {  
//                    OntProperty classPryOfIndiv = (OntProperty) classPryOfIndivs.next();  
                    System.out.println(classPryOfIndivs.next());  
//                    String classPryOfIndivstr = classPryOfIndiv.toString();  
//                    String info = null;  
//                    info = "实例URI:"  
//                            + indiv  
//                            + " 实例名："  
//                            + indiv.toString().substring(indiv.toString().indexOf("#") + 1) + "  实例所属类："  
//                            + classOfIndiv  
//                            +" 属性URI："  
//                            +classPryOfIndivstr;  
//                      
//                    // 获取这个实例的属性值  
//                    if (indiv.getPropertyValue(classPryOfIndiv) != null) {  
//                        String pryValueOfIndiv = indiv.getPropertyValue(classPryOfIndiv).toString();  
//                        /*判断对象属性或数据属性 
//                         * 如果实例属性值中包括"^^"和"#"，则为数据属性，否则为对象属性 
//                         */  
//                        if (pryValueOfIndiv.contains("^^")&&pryValueOfIndiv.contains("#")) {  
//                            info = info   
//                                    + "  属性值："  
//                                    + pryValueOfIndiv.substring(0, pryValueOfIndiv.indexOf("^^"));  
//                        }else{  
//                            info = info  
//                                    + "  属性值："  
//                                    + pryValueOfIndiv.substring(pryValueOfIndiv.indexOf("#") + 1);  
//                        }  
//                    } else {  
//                        info = info + "   无属性值";  
//                    }  
//                    //输出实例信息  
//                    System.out.println(info);  
                }  
          
            }  
  
        }  
        
        
        
  
        public static void main(String[] args) {  
        	String owlpath = "D:\\Fruits-lite.owl";  
    	
            System.out.println("--------------------------------------------列表1    类------------------------------");  
            new QueryOWL().getAllClasses(owlpath);  
            System.out.println("\r\n\r\n--------------------------------------------列表2    属性-----------------------------");  
            new QueryOWL().getAllProperties(owlpath);  
            System.out.println("\r\n\r\n--------------------------------------------列表3   属性特征--------------------------");  
            new QueryOWL().getAllProcharac(owlpath);  
            System.out.println("\r\n\r\n--------------------------------------------列表4    属性约束--------------------------");  
            new QueryOWL().getAllProcharac(owlpath);  
            System.out.println("\r\n\r\n--------------------------------------------列表5    实例------------------------------");  
            new QueryOWL().getAllIndividuals(owlpath);  
              
        }  
  
    }  
