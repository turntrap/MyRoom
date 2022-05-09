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
        	   sb.append(x + "\n");//将所有字符串放入缓冲区
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
		String []line = sb.toString().split("\n");//获得所有从文件中读取的数据
		int [][]data=new int[line.length][line.length];
		 for(int j=0;j<line.length;j++)
		 {
			 String[] strings=line[j].split("\t");
			 if(strings.length!=line.length)//对每个数据之间是否是\t以及是否行列数相等的判断				
				 return false;
			 for(int k = 0;k<strings.length;k++){
				 if(strings[k].matches("[0-9]+")&&Integer.valueOf(strings[k])!=0)//对每个数据是否是正整数的判断
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
			for(int k=0;k<line.length;k++)//相加求得要判断的总和
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
		 for(int j=0;j<rowsum.length;j++)//判断矩阵每一行和每一列的总和是否相等
		 {
			 if(rowsum[j]!=columnsum[j]||rowsum[j]!=z)
				 return false;
		 }
		 if(z!=diagonalrightsum||z!=diagonalleftsum)//判断左右对角线的总和是否相等
			return false;
		 return true;
		
	}
	
	public static boolean generateMagicSquare(int n) {//劳伯法生成幻方
		int magic[][];
		try {
			 magic = new int[n][n];
		}catch(NegativeArraySizeException e){//对n为负数的情况进行异常处理
			System.out.println("Exception thrown  :" + e);
			return false;
		}
		int row = 0, col = n / 2, i, j, square = n * n;//square即为所有要填的格子数。
		//将整个矩阵看作是一个上下相连，左右相连的矩阵，从矩阵第一行的中间格开始填数，
		//每次向他的右上方格子填下一个数
		//如果右上角的格子已经有数值，就在这个格子下方开始填。
		for (i = 1; i <= square; i++) {
			try {
		  magic[row][col] = i;
			}catch(ArrayIndexOutOfBoundsException e){//对n为偶数的情况进行异常处理
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
			  }catch(ArrayIndexOutOfBoundsException e){//对n为偶数的情况进行异常处理
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
		    System.out.print(magic[i][j] + "\t");//输出矩阵
		    System.out.println();
		}
		File file = new File("src\\P1\\txt\\6.txt"); //存储矩阵的文件
		FileWriter out;
		try {//对写文件异常的处理
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
