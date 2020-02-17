package com.example.demo.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
@EnableTransactionManagement
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void output(String dir, Model model) {

		List<User> users = userRepository.findAll();
		List<String> usersList = new ArrayList<String>();

		File file = new File(dir + "\\テストファイル.csv");
		try (OutputStreamWriter filewriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
				PrintWriter pw = new PrintWriter(new BufferedWriter(filewriter));) {

			//日付フォーマット変換
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E曜日");
			//取得結果をファイル書き込み用にリストに詰める
			usersList = users.stream().map(u -> u.getId() + "," + u.getName() + ","
					+ u.getCreated_date() + "," + u.getUpdated_date())
					.collect(Collectors.toList());

			//書き込み
			pw.println("ユーザーID,ユーザー名,作成日,更新日");
			usersList.forEach(u -> pw.println(u));

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			model.addAttribute("error", "ディレクトリが存在しません");
			e.printStackTrace();

		}
	}
}