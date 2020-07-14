package com.eazyftw.advancedreplay.utils;

import com.eazyftw.advancedreplay.utils.VersionUtil.VersionEnum;
import org.bukkit.Material;

public enum MaterialBridge {

	WATCH("CLOCK"),
	WOOD_DOOR("WOODEN_DOOR");

	private String materialName;

	private MaterialBridge(String materialName) {
		this.materialName = materialName;
	}

	@SuppressWarnings("deprecation")
	public static Material fromID(int id) {
		if (VersionUtil.isCompatible(VersionEnum.V1_13) || VersionUtil.isCompatible(VersionEnum.V1_14) || VersionUtil.isCompatible(VersionEnum.V1_15) || VersionUtil.isCompatible(VersionEnum.V1_16)) {
			for (Material mat : Material.values()) {
				if (mat.getId() == id) return mat;
			}

			return null;

		} else {
			return Material.getMaterial(id);
		}
	}

	public Material toMaterial() {
		return Material.valueOf(this.toString());

	}

	public String getMaterialName() {
		return materialName;
	}
}
