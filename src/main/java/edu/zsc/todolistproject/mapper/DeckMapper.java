package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Deck;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeckMapper {
    @Select("select * from deck where id=#{id}")
    Deck getDeckById(Long id);

    @Delete("delete from deck where id=#{id}")
    int deleteDeckById(Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into deck(title,board_id) values(#{title},#{boardId})")
    int insertDeck(Deck deck);

    @Update("update deck set title=#{title},board_id=#{boardId} where id=#{id}")
    int updateDeck(Deck deck);

    @Select("select * from deck")
    List<Deck> getDecks();

    @Select("select * from deck where board_id=#{boardId}")
    List<Deck> getDecksByBoard(Long boardId);
}
