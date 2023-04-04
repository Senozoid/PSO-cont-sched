# PSO for Container Scheduling

This program attempts to demonstrate how Particle Swarm Optimization might be implemented for container scheduling. \
Since we do not actually have a cloud implementation, the program has to rely on user input to define the scheduling problem. \
It is almost complete, and currently looks like [this](https://youtu.be/KRO44ZWNkkk).

## How to run this program

1. Install [Java](https://adoptium.net/), if you don't have it installed
2. Open terminal in `src` folder
3. If you are doing this the first time, run `javac *`, otherwise skip this step
4. Run `java Optimizer`

## How this program works

Particle Swarm Optimization needs 4 things:

1. The search space
2. The swarm of particles
3. Parameters to tell the swarm how to search
4. A fitness function to tell the swarm what to search for

### The space

The search space is defined by the given scenario. Every position in the search space corresponds to a scheduling configuration. Each container corresponds to a dimension, or an axis. If there are two containers, for example, it is a 2D space. If the x and y coordinates of a position in this 2D space are 4 and 5 respectively, then that position corresponds to the configuration where container x is assigned to host 4 and container y is assigned to host 5.

### The particles

The swarm is akin to a search party, and the particles are units spreading out to search for the best spot. They share a hive mind, so at any point during the search, they know where the best spot found so far is, and have a tendency to go towards it. Each particle also has a tendency to return to the best spot it found on its own. Alongside these tendencies, each particle also has a tendency to stay on the course it is already on.

### The parameters

These three tendencies are respectively known as the social component, the cognitive component and the inertia weight. The exact value of these parameters are of course taken from the user input. The inertia has to be less than 1, so that the particles do not accelerate towards the first direction they find themselves moving at. And for some variety, a random factor each is multiplied to the social and cognitive components during the calculations.

### The function

A fitness "mapping" is first automatically generated by the program from the first set of user inputs, which the user can choose to keep or overwrite. The map is basically just a matrix or a 2D array that stores information about potential costs of all pairings of host and container. For example, if `map[3,7]`=72.6, that means the cost of running container 3 on host 7 is 72.6 units. \
The "topography" of the generated map resembles an inverted pentahedron. Imagine a sheet of paper folded in half, unfolded, rotated 90 degrees, folded and unfolded again. It should now have two perpendicular creases, both "closing" upwards like a book. This shape has the advantage that there is only a single best solution (the host that represents one of the creases), which makes it easy to test and demonstrate how well the model is working.

### Other details

1. Particle Swarm Optimization does not guarantee the best possible solution, because the swarm may not have found it.
2. The coordinates of a particle at any given epoch are always non-negative integers, because they represent indices.
3. The fitness mapping, and the positions and velocities of each particle at each epoch, is stored in _log.txt_.

## Future plans

1. Implement GUI
2. Use something like CloudSim
