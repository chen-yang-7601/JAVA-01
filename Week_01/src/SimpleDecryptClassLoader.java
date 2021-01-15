import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



/**
 *  @author yhh
 *
 */
public class SimpleDecryptClassLoader extends ClassLoader {

	static final int CRYPT_KEY = 255;
	
	public SimpleDecryptClassLoader() 
	{
		super(SimpleDecryptClassLoader.class.getClassLoader());
	}
	
	
    public Class<?> loadFromEncryptedFile(String fname,String className) throws ClassFormatError
    {
    	FileInputStream sf = null;
    	try {
			sf = new FileInputStream(new File(fname));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int val;
			while( (val = sf.read())!=-1) 
			{	
				out.write(val);
			}
			sf.close();
			byte[] outcode = decode(out.toByteArray());
	        return defineClass(className, outcode, 0,outcode.length);			
    	}catch(IOException e) {
    		e.printStackTrace();
    		throw new ClassFormatError("Cannot load the class file "+ fname);
    	}finally {
    		if (null!= sf)    		
    		{
    			try {
    				sf.close();
    			}catch(IOException e1)
    			{
    				throw new ClassFormatError("Cannot find the class file "+ fname);
    			}
    		}
    	}
    }
    
    
    private byte[] decode(byte[] org)
    {
    	byte[] decoded = new byte[org.length];
    	for (int i=0; i<org.length; i++)
    	{
    		decoded[i] =(byte) (CRYPT_KEY - org[i] );
    	}
    	return decoded;
    }
    
	public static void main(String[] args) 
	{
		SimpleDecryptClassLoader loader = new SimpleDecryptClassLoader();
		String fname = "E:\\01-Jennifer\\wks\\Hello.xlass";

		try {
			Class<?> cryptedClass = loader.loadFromEncryptedFile(fname, "Hello");
			//Invoke its method
			Method method = cryptedClass.getDeclaredMethod("hello");
			method.invoke(cryptedClass.newInstance());
		}catch(NoSuchMethodException
				|IllegalAccessException
				|InstantiationException
				|InvocationTargetException
				e) {
			e.printStackTrace();
		}
	}

}
