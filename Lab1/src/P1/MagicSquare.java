package P1;

import java.io.* ;

public class MagicSquare {
	
	static boolean  isLegalMagicSquare(String fileName)
	{
		BufferedReader buffer = null;
		StringBuilder sb = new StringBuilder();
		 String x=null;       
		 try {
           InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(new File("src/P1/txt/"+ fileName)));
           buffer = new BufferedReader(inputStreamReader);         
           while((x = buffer.readLine() )!= null){
        	   sb.append(x + "\n");//�������ַ������뻺����
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
		String []line = sb.toString().split("\n");//������д��ļ��ж�ȡ������
		int [][]data=new int[line.length][line.length];
		 for(int j=0;j<line.length;j++)
		 {
			 String[] strings=line[j].split("\t");
			 if(strings.length!=line.length)//��ÿ������֮���Ƿ���\t�Լ��Ƿ���������ȵ��ж�				
				 return false;
			 for(int k = 0;k<strings.length;k++){
				 if(strings[k].matches("[0-9]+")&&Integer.valueOf(strings[k])!=0)//��ÿ�������Ƿ������������ж�
                    data[j][k] = Integer.valueOf(strings[k]);
				 else									
					 return false;
            }
		 }
		 int []rowsum=new int[line.length];
		 int[]columnsum=new int[line.length];
		 int diagonalleftsum=0,diagonalrightsum=0;
		 for(int j=0;j<line.length;j++)
		 {
			for(int k=0;k<line.length;k++)//������Ҫ�жϵ��ܺ�
			{
				rowsum[j]+=data[j][k];
				columnsum[k]+=data[j][k];
				if(j==k)
					diagonalrightsum+=data[j][k];
				if(j+k+1==line.length)
					diagonalleftsum+=data[j][k];
			}
		 }
		 int z=rowsum[0];
		 for(int j=0;j<rowsum.length;j++)//�жϾ���ÿһ�к�ÿһ�е��ܺ��Ƿ����
		 {
			 if(rowsum[j]!=columnsum[j]||rowsum[j]!=z)
				 return false;
		 }
		 if(z!=diagonalrightsum||z!=diagonalleftsum)//�ж����ҶԽ��ߵ��ܺ��Ƿ����
			return false;
		 return true;
		
	}
	
	public static boolean generateMagicSquare(int n) {//�Ͳ������ɻ÷�
		int magic[][];
		try {
			 magic = new int[n][n];
		}catch(NegativeArraySizeException e){//��nΪ��������������쳣����
			System.out.println("Exception thrown  :" + e);
			return false;
		}
		int row = 0, col = n / 2, i, j, square = n * n;//square��Ϊ����Ҫ��ĸ�������
		//��������������һ���������������������ľ��󣬴Ӿ����һ�е��м��ʼ������
		//ÿ�����������Ϸ���������һ����
		//������Ͻǵĸ����Ѿ�����ֵ��������������·���ʼ�
		for (i = 1; i <= square; i++) {
			try {
		  magic[row][col] = i;
			}catch(ArrayIndexOutOfBoundsException e){//��nΪż������������쳣����
				  System.out.println("Exception thrown  :" + e);
					return false;
			  }
		  if (i % n == 0)
		    row++;
		  else {
			  try {
				  if (row == 0)
				      row = n - 1;
				    else
				      row--;
			  }catch(ArrayIndexOutOfBoundsException e){//��nΪż������������쳣����
				  System.out.println("Exception thrown  :" + e);
					return false;
			  }
		if (col == (n - 1))
		  col = 0;
		else
		  col++;
		}
		}
		for (i = 0; i < n; i++) {
		  for (j = 0; j < n; j++)
		    System.out.print(magic[i][j] + "\t");//�������
		    System.out.println();
		}
		File file = new File("src\\P1\\txt\\6.txt"); //�洢������ļ�
		FileWriter out;
		try {//��д�ļ��쳣�Ĵ���
			out = new FileWriter(file);
			for(int x=0;x<n;x++){
			  for(int y=0;y<n;y++){
				out.write(magic[x][y]+"\t");
			  }
				out.write("\r\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(isLegalMagicSquare("1.txt"));
		System.out.println(isLegalMagicSquare("2.txt"));
		System.out.println(isLegalMagicSquare("3.txt"));
		System.out.println(isLegalMagicSquare("4.txt"));
		System.out.println(isLegalMagicSquare("5.txt"));
		generateMagicSquare(7);
		System.out.println(isLegalMagicSquare("6.txt"));
	}

}
