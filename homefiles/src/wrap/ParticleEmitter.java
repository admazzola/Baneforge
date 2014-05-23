package wrap;

public class ParticleEmitter {
	
	
	boolean IsActive=false;
	final int max_distance = 50;
	int num_particles = 9;
	
	int speed=200;   //from 1 to 999
	int width;
	int height;
	
	int mapX;
	int mapY;
	
int count = 0;

	float r;
	float g;
	float b;
	float alpha;
	
	/*
	int quantity = 1;
	
	int[] distance_from_origin = new int [9];
	*/
	

	double[] particle_angle = new double [40];//the seed of each particle
	
}
