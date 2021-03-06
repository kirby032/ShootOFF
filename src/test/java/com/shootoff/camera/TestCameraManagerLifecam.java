package com.shootoff.camera;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.shootoff.camera.Shot.ShotColor;
import com.shootoff.camera.shotdetection.JavaShotDetector;
import com.shootoff.config.Configuration;
import com.shootoff.config.ConfigurationException;
import com.shootoff.gui.MockCanvasManager;

public class TestCameraManagerLifecam extends ShotDetectionTestor {
	private Configuration config;
	private MockCanvasManager mockManager;
	private boolean[][] sectorStatuses;

	@Rule public ErrorCollector collector = new ErrorCollector();

	@Before
	public void setUp() throws ConfigurationException {
		config = new Configuration(new String[0]);
		// Minimize logging attempts because Travis-CI will kill us
		// due to verbose output. To re-enable log outputs you also
		// need to comment out the code in ShotDetectionTestor.setUpBaseClass()
		// that disables all loggers.
		config.setDebugMode(false);
		mockManager = new MockCanvasManager(config, true);
		sectorStatuses = new boolean[JavaShotDetector.SECTOR_ROWS][JavaShotDetector.SECTOR_COLUMNS];

		for (int x = 0; x < JavaShotDetector.SECTOR_COLUMNS; x++) {
			for (int y = 0; y < JavaShotDetector.SECTOR_ROWS; y++) {
				sectorStatuses[y][x] = true;
			}
		}

	}

	@Test
	public void testLifecamIndoorGreen() {
		List<Shot> shots = findShots("/shotsearcher/lifecam-indoor-green.mp4", Optional.empty(), mockManager, config,
				sectorStatuses);

		List<Shot> requiredShots = new ArrayList<Shot>();
		requiredShots.add(new Shot(ShotColor.GREEN, 432.7, 309.5, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 295.5, 320.2, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 75.0, 339.3, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 141.2, 208.7, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 295.3, 234.1, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 471.8, 226.6, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 443.4, 100.9, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 257.8, 109.1, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 83.3, 79.3, 0, 2));

		super.checkShots(collector, shots, requiredShots, new ArrayList<Shot>(), false);
	}

	@Test
	public void testLifecamOutdoorGreen() {
		List<Shot> shots = findShots("/shotsearcher/lifecam-outdoor-green.mp4", Optional.empty(), mockManager, config,
				sectorStatuses);

		List<Shot> requiredShots = new ArrayList<Shot>();
		requiredShots.add(new Shot(ShotColor.GREEN, 449.1, 324.5, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 276.6, 325.1, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 97.6, 333.3, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 143.9, 197.6, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 304.3, 225.7, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 441.3, 226.5, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 441.2, 109.5, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 294.3, 121.4, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 112.8, 111.4, 0, 2));

		super.checkShots(collector, shots, requiredShots, new ArrayList<Shot>(), false);
	}

	@Test
	public void testLifecamSafariGreen() {
		List<Shot> shots = findShots("/shotsearcher/lifecam-safari-green.mp4", Optional.empty(), mockManager, config,
				sectorStatuses);

		List<Shot> requiredShots = new ArrayList<Shot>();
		requiredShots.add(new Shot(ShotColor.GREEN, 413.0, 265.4, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 266.1, 298.2, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 87.8, 312.6, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 108.4, 192.3, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 257.9, 213.8, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 428.2, 220.5, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 433.2, 91.4, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 310.4, 113.0, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 117.2, 107.4, 0, 2));

		super.checkShots(collector, shots, requiredShots, new ArrayList<Shot>(), true);
	}

	@Test
	public void testLifecamMotion() {
		List<Shot> shots = findShots("/shotsearcher/lifecam-motion-in-room.mp4", Optional.empty(), mockManager, config,
				sectorStatuses);

		// This is noise but we can't get rid of it without really messing up
		// other tests.
		List<Shot> optionalShots = new ArrayList<Shot>();
		optionalShots.add(new Shot(ShotColor.GREEN, 440.9, 350.7, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 373.5, 390.1, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 354.5, 387.6, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 444.3, 330.8, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 435.2, 391.5, 0, 2));

		super.checkShots(collector, shots, new ArrayList<Shot>(), optionalShots, true);
	}

	@Test
	public void testLifecamDuelTree() {
		List<Shot> shots = findShots("/shotsearcher/lifecam-indoor-tree-green.mp4", Optional.empty(), mockManager,
				config, sectorStatuses);

		List<Shot> requiredShots = new ArrayList<Shot>();
		requiredShots.add(new Shot(ShotColor.GREEN, 261.9, 119.4, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 350.4, 275.5, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 332.6, 308.1, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 316.6, 284.6, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 266.8, 252.4, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 324.9, 223.4, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 330.1, 152.0, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 325.4, 162.5, 0, 2));
		requiredShots.add(new Shot(ShotColor.GREEN, 328.7, 155.5, 0, 2));

		List<Shot> optionalShots = new ArrayList<Shot>();
		// This is noise on the table in the middle
		optionalShots.add(new Shot(ShotColor.GREEN, 268.3, 264.1, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 268.3, 264.1, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 295.2, 222.7, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 253.5, 192.0, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 268.0, 181.1, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 268.1, 119.4, 0, 2));

		// From the plate moving
		optionalShots.add(new Shot(ShotColor.GREEN, 311.2, 221.1, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 262.9, 123.8, 0, 2));
		optionalShots.add(new Shot(ShotColor.GREEN, 258.5, 181.8, 0, 2));

		super.checkShots(collector, shots, requiredShots, optionalShots, false);
	}
}