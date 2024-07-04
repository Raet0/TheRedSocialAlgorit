import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecommendationEnginee<T extends User> {

// Método para recomendar amigos
public List<T> recommendFriends(NodeGraph<T> node, Graph<T> graph) {
    List<T> recommendations = new ArrayList<>();
    for (NodeGraph<T> potentialFriendNode : graph.getNodes()) {
        T potentialFriend = potentialFriendNode.getUser();
        if (!node.getFriends().contains(potentialFriendNode) && !node.getUser().equals(potentialFriend)) {
            double similarity = calculateSimilarity(node.getUser(), potentialFriend);
            if (similarity > 0.3) { // Umbral de recomendación
                recommendations.add(potentialFriend);
            }
        }
    }
    return recommendations;
}
// Método para calcular la similitud
    public double calculateSimilarity(T user1, T user2) {
        Set<String> interests1 = new HashSet<>(user1.getInterests());
        Set<String> interests2 = new HashSet<>(user2.getInterests());
        Set<String> intersection = new HashSet<>(interests1);
        intersection.retainAll(interests2);
        Set<String> union = new HashSet<>(interests1);
        union.addAll(interests2);

        double resp = (double) intersection.size() / union.size();
        System.out.println("Valor- "+ resp);
        return resp;
    }

}
