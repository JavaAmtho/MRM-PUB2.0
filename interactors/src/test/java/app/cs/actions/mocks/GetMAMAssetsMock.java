package app.cs.actions.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.mam.AssetsRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class GetMAMAssetsMock implements Interactor{

	private AssetsRepository mamRepository;

	@Autowired
	public GetMAMAssetsMock(AssetsRepository mamRepository) {
		this.mamRepository = mamRepository;
	}

	public ResponseModel execute(RequestModel model) {
		
		return new StringResponse("		{\"755\":{\"id\":\"755\",\"label\":\"Benchmark\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=776efa755@CSLive&rand=4b152a783a8f094cf4f78dd56d919073&force=true&width=32&height=48\",\"isfolder\":true,\"description\":\"Folder\",\"service\":\"mam\"},\"177\":{\"id\":\"177\",\"label\":\"Cover\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=bc355a177@CSLive&rand=b27d3a2da84c90516fa50b39d713b72d&force=true&width=32&height=48\",\"isfolder\":true,\"description\":\"Folder\",\"service\":\"mam\"},\"388\":{\"id\":\"388\",\"label\":\"Mixed\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=b01d9a388@CSLive&rand=592aa1a022aeb453f558993684712546&force=true&width=32&height=48\",\"isfolder\":true,\"description\":\"Folder\",\"service\":\"mam\"},\"6915\":{\"id\":\"6915\",\"label\":\"News\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=8b3e0a6915@CSLive&rand=30534c633a7eae2ae6a75350f71194cf&force=true&width=32&height=48\",\"isfolder\":true,\"description\":\"Folder\",\"service\":\"mam\"},\"1595\":{\"id\":\"1595\",\"label\":\"People\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=75ae0a1595@CSLive&rand=be00dbc6a792e2797a7e55a28e4c6ff2&force=true&width=32&height=48\",\"isfolder\":true,\"description\":\"Folder\",\"service\":\"mam\"},\"1167\":{\"id\":\"1167\",\"label\":\"Products\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=d6280a1167@CSLive&rand=6b63bd360dfe8e2430030e3f960b66c1&force=true&width=32&height=48\",\"isfolder\":true,\"description\":\"Folder\",\"service\":\"mam\"},\"769\":{\"id\":\"769\",\"label\":\"Blue.jpg\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=36207a769@CSLive&rand=549b756448e9618342caa4039e90e55a&force=true&width=32&height=48\",\"isfolder\":false,\"description\":\"jpg\",\"service\":\"mam\"},\"770\":{\"id\":\"770\",\"label\":\"Green.jpg\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=d04fca770@CSLive&rand=f28cb8568fe0a0dd3d6117d51e3a40e5&force=true&width=32&height=48\",\"isfolder\":false,\"description\":\"jpg\",\"service\":\"mam\"},\"771\":{\"id\":\"771\",\"label\":\"Orange.jpg\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=0704fa771@CSLive&rand=9ae1c3edfcd786f640e85b6d4a0f0a56&force=true&width=32&height=48\",\"isfolder\":false,\"description\":\"jpg\",\"service\":\"mam\"},\"772\":{\"id\":\"772\",\"label\":\"Pink.jpg\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=4b512a772@CSLive&rand=9ae1c3edfcd786f640e85b6d4a0f0a56&force=true&width=32&height=48\",\"isfolder\":false,\"description\":\"jpg\",\"service\":\"mam\"},\"773\":{\"id\":\"773\",\"label\":\"Purple.jpg\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=522a0a773@CSLive&rand=4dfe9f59c2fb1be0577b695afa990c82&force=true&width=32&height=48\",\"isfolder\":false,\"description\":\"jpg\",\"service\":\"mam\"},\"2655\":{\"id\":\"2655\",\"label\":\"White.tif\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=dd267a2655@CSLive&rand=dbfe6acdf346c99e8201d769938040cf&force=true&width=32&height=48\",\"isfolder\":false,\"description\":\"tif\",\"service\":\"mam\"}}");

	}

}