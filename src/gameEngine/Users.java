import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Users implements Runnable
{
	DataOutputStream out;
	DataInputStream in;
	Users[] user = new Users[10];
	String name;
	public Users(DataOutputStream out, DataInputStream in, Users[] user) 
	{
		this.out = out;
		this.in = in;
		this.user = user;
	}

	
	public void run() 
	{
		try {
			name = in.readUTF();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(true) 
		{ 
			try 
				{
					String message = in.readUTF();
					for (int i = 0; i < 10; i++) 
						{
							if (user[i] != null) 
								{
									user[i].out.writeUTF(name + ":" + message);
								}
						}
				} catch (IOException e) 
			{
				this.out = null;
				this.in = null;
			}
		}
	}
}
