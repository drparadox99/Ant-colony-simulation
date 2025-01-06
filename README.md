# Ant colony simulation
Ant colony simulation in java language !

<img width="1012" alt="Capture d’écran 2025-01-06 à 18 16 42" src="https://github.com/user-attachments/assets/277c62ee-4054-42d4-afce-9c8ec806ad58" />

# Project Overview

This project consists of simulating an ant colony, with at least three types of ants:

Queens: They do not move, live for several years, and produce workers and warriors.
Workers: They live for a few weeks, search for food or materials, and bring it back to the nest.
Warriors: They live for a few months, search for threats (e.g., large animals or other ant colonies), and attack intruders.
Each ant type consumes food from the nest, and if food is scarce, the queen is prioritized, followed by workers, with warriors being the least prioritized. The simulation includes various entities like food, threats, and materials, which are randomly distributed on a grid representing the terrain. The project aims to simulate the behaviors of different ants and how they interact with their environment.

# Features

Ant Types: Queens, workers, and warriors, each with distinct behaviors and lifespans.
Ant Behavior:
Workers search for food and materials to strengthen the nest.
Warriors patrol the area to attack threats like animals. Threats lose health when attacked and die if sufficiently attacked.
Resource Management: The ants consume food stored in the nest, with different consumption rates for each ant type.
Dynamic Terrain: The terrain is divided into small squares that can contain various items (food, materials, threats) or ants. The content of the squares can change over time.
Nest Prioritization: If food is insufficient, priority is given to the queen, followed by workers, and then warriors.
Pheromone System: Workers use pheromones to return to the nest after finding resources. They also collaborate if a resource is too large to carry alone.
Threats: Different types of animals represent threats, each with unique behavior (movement, speed, etc.).
