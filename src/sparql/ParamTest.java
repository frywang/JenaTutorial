package sparql;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;  
import org.junit.AfterClass;  
import org.junit.Before;  
import org.junit.BeforeClass;  
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import junit.framework.Assert;  


		
//指定@RunWith  
@RunWith(Parameterized.class)  
public class ParamTest {  

  //定义成员变量，i为测试参数，j为测试结果  
  private int i;  
  private int j;  

  //构造函数  
  public ParamTest(int i, int j) {  
      super();  
      this.i = i;  
      this.j = j;  
  }  

  //测试数据集合，注意使用的注解，数据结构及次序  
  @Parameters  
  public static Collection data() {  
      return Arrays.asList(new Object[][]{{1,2},{3,4},{4,6}});  
  }  
  @Test  
  public void testMethod1() {  
      System.out.println(i);  
      System.out.println(j);  
      //简单测试，只测试参数加1会不会等于预期结果  
      Assert.assertEquals(i+1, j);  
  }  
}  
