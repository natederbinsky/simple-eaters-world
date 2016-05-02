package edu.umich.eecs.soar.tutorial;

import sml.Agent;
import sml.Kernel;
import sml.smlRunStepSize;

public class SimpleEaters {

	public static void main(String[] args) {
		final int kernelPort = 27314;
		final String agentName = "Eva";
		final int agentRandomSeed = 41372;

		final Orientation initialOrientation = Orientation.east;
		final int initialX = 1;
		final int initialY = 2;

		final MapObject[][] map = { 
			{ MapObject.purple,			null,					MapObject.red, 			MapObject.green },
			{ MapObject.purple, 		MapObject.red, 			MapObject.red, 			MapObject.wall },
			{ MapObject.purple, 		MapObject.wall,			MapObject.red, 			MapObject.green }, 
		};
		
		final int sleepMsecs = 10;

		final Kernel kernel = Kernel.CreateKernelInNewThread(kernelPort);
		final Agent agent = kernel.CreateAgent(agentName);
		agent.ExecuteCommandLine("srand " + agentRandomSeed);

		final SimpleEatersWorld world = new FullSimpleEatersWorld(agent, map, initialOrientation, initialX, initialY, sleepMsecs);
		world.setPoints(MapObject.purple, 10);
		world.setPoints(MapObject.red, 5);
		world.setWallPenalty(1);
		world.setTimePenalty(1);

		agent.SpawnDebugger(kernelPort, "lib/soar/SoarJavaDebugger.jar");
		agent.LoadProductions("agent.soar");
		agent.RunSelf(1, smlRunStepSize.sml_ELABORATION);

//		agent.KillDebugger();
//		kernel.Shutdown();
//		System.exit(0);
	}

}
