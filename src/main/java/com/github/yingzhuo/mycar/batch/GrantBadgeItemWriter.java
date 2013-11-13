package com.github.yingzhuo.mycar.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.repository.UserRepo;

public class GrantBadgeItemWriter implements ItemWriter<User> {

	private UserRepo userRepo;
	
	@Override
	public void write(List<? extends User> items) throws Exception {
		for (User user : items) {
			writeEach(user);
		}
	}

	private void writeEach(User user) throws Exception {
		userRepo.saveAndFlush(user);
	}
	
	// -------------------------------------------------------------------------------------

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
}
