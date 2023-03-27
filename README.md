# PSO for Container Scheduling (INCOMPLETE)
This branch is for my personal modifications to this code, meant for use in a college project. \
You might want to fork from the [original repository](https://github.com/LazoCoder/Particle-Swarm-Optimization) instead. \
You can also take a look at the [branch used for my pull requests](https://github.com/Senozoid/LazoCoder-PSO-fork/tree/changes) to the original.

# What this does different
This program attempts to demonstrate how PSO can be implemented for container scheduling. \
The 2 most important differences from the original code are:

* The search space is not limited to 3 dimensions
* Each coordinate can have only integral values

Both of these differences are necessary for the objective of my program: \
The number of dimensions = number of containers \
The value of each coordinate = index of a host

## Usage
Navigate directly above the PSO folder.

To run it with the default parameters:
```
java PSO.Main
```
To run it with custom parameters:
```
java PSO.Main -p
```
