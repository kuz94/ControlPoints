
import java.util.Scanner;

public class ControlPoints {
	
	public static void main(String[] args) {
		int n;
		int x0;
		int[] points;
		int dist;
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		x0 = input.nextInt();
		points = new int[n];
		for(int i = 0; i < n; i++) {
			points[i] = input.nextInt();
		}
		mergeSort(points, n);
		dist = calculateDists(points, x0, n);
		System.out.println(dist);
	}

	public static int calculateDists(int[] x, int x0, int n) {
		if (n == 1) {
			return 0;
		}
		if (x0 > x[n-1]) {
			return x0 - x[1];
		}
		if (x0 < x[0]) {
			return x[n-2] - x0;
		}
		int[] ver = new int[6];
		int nv = 0;
		ver[nv++] = x0 + x[n-2] - (x[0] << 1);
		ver[nv++] = (x[n-1] << 1) - x[1] - x0;
		if (n > 2) {
			if (x0 > x[1]) {
				ver[nv++] = x0 + x[n-1] - (x[1] << 1);
			}
			if (x0 < x[n-2]) {
				ver[nv++] = (x[n-2] << 1) - x[0] - x0;
			}
		}
		if (x0 <= x[1]) {
			ver[nv++] = x[n-1] - x0;
		}
		if (x0 >= x[n-2]) {
			ver[nv++] = x0 - x[0];
		}
		return mergeSort(ver, nv)[0];
	}
	
	public static int[] mergeSort(int[] x, int n) {
		for (int k = 1; k < n; k *= 2) {
			for(int i = 0; i + k < n; i += 2*k) {
				merge(x, i, i + k, n);
			}
		}
		return x;
	}
	
	public static void merge(int[] x, int in1, int in2, int n) {
		int l = in2 - in1; 
		int l1 = l;
		int l2 = (in2 + l < n) ? l : n - in2;
		int[] y = new int[l1 + l2];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < l1 && j < l2) {
			if (x[in1 + i] < x[in2 + j]) {
				y[k++] = x[in1 + i++];
			} else {
				y[k++] = x[in2 + j++];
			}
		}
		while (i < l1) {
			y[k++] = x[in1 + i++];
		}
		while (j < l2) {
			y[k++] = x[in2 + j++];
		}
		for(int z = 0; z < k; z++) {
			x[in1 + z] = y[z];
		}
	}	
}

