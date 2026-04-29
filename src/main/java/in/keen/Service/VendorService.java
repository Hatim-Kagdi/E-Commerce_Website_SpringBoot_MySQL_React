package in.keen.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import in.keen.DTO.VendorStatsDTO;
import in.keen.Repository.OrderRepository;

@Service
public class VendorService {
	
	@Autowired private OrderRepository orderRepository;

	public VendorStatsDTO getVendorAnalytics(int userId) {
		VendorStatsDTO dto = new VendorStatsDTO();
		
		Double revenue = orderRepository.getRevenueByVendor(userId);
		if(revenue != null) {
			dto.setTotalRevenue(revenue);
		}else {
			dto.setTotalRevenue(0.0);
		}
		
		Integer sold = orderRepository.getItemsSoldByVendor(userId);
		if(sold != null) {
			dto.setTotalItemsSold(sold);
		}else {
			dto.setTotalItemsSold(0);
		}
		
		dto.setPendingOrders(orderRepository.countByVendorUserIdAndStatus(userId, "PLACED"));
		
		dto.setTotalOrders(orderRepository.countByVendorUserId(userId));
		
		return dto;
	}

}
