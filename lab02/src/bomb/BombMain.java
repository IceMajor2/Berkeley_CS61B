package bomb;

import common.IntList;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        IntList intPassword = IntList.of(0, 9, 3, 0, 8);
        if (phase >= 1) {
            b.phase1(intPassword); // Figure this out too
        }
        StringBuilder password = new StringBuilder("");
        Random r = new Random(1337);
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < 100000) {
            numbers.add(r.nextInt());
        }
        for(int number : numbers) {
            password.append(Integer.toString(number));
            password.append(" ");
        }
        if (phase >= 2) {
            b.phase2(password.toString());
        }
    }
}
