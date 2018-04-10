/**************************************************
 @ Author: Anadi Sharma

 Main.java: DCT and IDCT
 **************************************************/

public class Main {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		double[][] A = {
				{ 139, 144, 149, 153, 155, 155, 155, 155 },
				{ 144, 151, 153, 156, 159, 156, 156, 156 },
				{ 150, 155, 160, 163, 158, 156, 156, 156 },
				{ 159, 161, 162, 160, 160, 159, 159, 159 },
				{ 159, 160, 161, 162, 162, 155, 155, 155 },
				{ 161, 161, 161, 161, 160, 157, 157, 157 },
				{ 162, 162, 161, 163, 162, 157, 157, 157 },
				{ 162, 162, 161, 161, 163, 158, 158, 158 }
		};
		
		System.out.println("\n\nOriginal Matrix :");
		System.out.println("-----------------------------------------------------------");
		for ( int g = 0; g < 8; g++ ) {
			for ( int h = 0; h < 8; h++ ) {
				System.out.print(A[g][h] + "\t");
			}
			System.out.println();
		}
		
		double[][] B = dct(A);
		double[][] C = idct(B);
		compare(A, C);
	}

	/**
	 * Discrete Cosine Transform
	 * @param A
	 * @return
	 */
	public static double[][] dct(double A[][]) {
		
		double B[][] = new double[8][8];

		System.out.println("\n\nDiscrete Cosine Transform Matrix :");
		System.out.println("--------------------------------------------------------------");
		for ( int u = 0; u < 8; u++ ) {
			for ( int v = 0; v < 8; v++ ) {
				
				double cu = ( u == 0 ) ? Math.sqrt(2) / 2 : 1;
				double cv = ( v == 0 ) ? Math.sqrt(2) / 2 : 1;
				
				double sum = 0;
				for ( int i = 0; i < 8; i++ ) {
					for ( int j = 0; j < 8; j++ ) {
						sum += Math.cos(( (2 * i + 1) * u * Math.PI ) / 16) *
								Math.cos(( (2 * j + 1) * v * Math.PI ) / 16) *
								( A[i][j] - 128 );
					}
				}

				B[u][v] = ( ( cu * cv ) / 4 ) * sum ;
				System.out.print(Double.parseDouble(String.format("%.1f", B[u][v])) + "\t");
			}
			
			System.out.println();
		}
		
		return B;
	}
	
	/**
	 * Inverse Discrete Cosine Transform
	 * @param B
	 * @return
	 */
	public static double[][] idct(double B[][]) {

		double C[][] = new double[8][8];
		
		System.out.println("\n\nInverse Discrete Cosine Transform Matrix :");
		System.out.println("--------------------------------------------------------------");
		for ( int u = 0; u < 8; u++ ) {
			for ( int v = 0; v < 8; v++ ) {
				
				double cu = ( u == 0 ) ? Math.sqrt(2) / 2 : 1;
				double cv = ( v == 0 ) ? Math.sqrt(2) / 2 : 1;
				
				for ( int i = 0; i < 8; i++ ) {
					for ( int j = 0; j < 8; j++ ) {
						C[i][j] += ( ( cu * cv ) / 4.0 ) * 
								Math.cos(( (2 * i + 1) * u * Math.PI ) / 16) *
								Math.cos(( (2 * j + 1) * v * Math.PI ) / 16) *
								( B[u][v] ) ;
					}
				}
			}
		}
		
		for ( int g = 0; g < 8; g++ ) {
			for ( int h = 0; h < 8; h++ ) {
				C[g][h] = Double.parseDouble(String.format("%.1f", C[g][h] + 128)) ;
				System.out.print(C[g][h] + "\t");
			}
			System.out.println();
		}
		return C;
	}
	
	/**
	 * Compare Input and Result
	 * @param A
	 * @param C
	 */
	public static void compare(double[][] A, double[][] C) {
		for ( int i = 0; i < 8; i++ ) {
			for ( int j = 0; j < 8; j++ ) {
				if ( A[i][j] != C[i][j] ) {
					System.out.println("\n\nMatrix A and C are not identical.");
					return;
				}
			}
		}
		
		System.out.println("\n\nMatrix A and C are identical.");
	}

}
