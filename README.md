# Queue-Simulator
  Design and implement a simulation application aiming to analyse queuing based systems for determining and minimizing clients’ waiting time.

  The application should simulate (by defining a simulation time 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛) a series of N clients arriving for service, entering Q queues, waiting, being served and finally leaving the queues. All clients are generated when the simulation is started, and are characterized by three parameters: ID (a number between 1 and N), 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 (simulation time when they are ready to go to the queue; i.e. time when the client finished shopping) and 𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 (time interval or duration needed to serve the client; i.e. waiting time when the client is in front of the queue). The application tracks the total time spent by every client in the queues and computes the average waiting time. Each client is added to the queue with minimum waiting time when its 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 time is greater than or equal to the simulation time (𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙≥𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛).

  The following data should be considered as input data for the application that should be inserted by the user in the application’s user interface:
- Number of clients (N);
- Number of queues (Q);
- Simulation interval (𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛𝑀𝐴𝑋);
- Minimum and maximum arrival time (𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙𝑀𝐼𝑁≤𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙≤𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙𝑀𝐴𝑋);
- Minimum and maximum service time (𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒𝑀𝐼𝑁≤𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒≤𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒𝑀𝐴𝑋);
