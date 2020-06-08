package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Card;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardMapper {
    @Select("select * from card where id=#{id}")
    Card getCardById(Long id);

    @Delete("delete from card where id=#{id}")
    int deleteCardById(Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into card(title,deck_id) values(#{title},#{deckId})")
    int insertCard(Card card);

    @Update("update card set title=#{title},deck_id=#{deckId} where id=#{id}")
    int updateCard(Card card);

    @Update("update card set title=#{title} where id=#{id}")
    int updateCardTitle(Long id, String title);

    @Update("update card set description=#{description} where id = #{id}")
    int updateCardDesc(Long id,String description);

    @Select("select * from card")
    List<Card> getCards();

    @Select("select * from card where deck_id=#{deckId}")
    List<Card> getCardsByDeck(Long deckId);

}
